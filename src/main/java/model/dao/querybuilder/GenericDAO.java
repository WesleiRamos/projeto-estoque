package model.dao.querybuilder;

import conexao.Conexao;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import views.utils.Alerts;

abstract public class GenericDAO<I extends GenericDAO> extends QueryBuilder {

    private Class<I> subClass;
    private final HashMap<String, Object> values = new HashMap<>();

    public GenericDAO() {
        this.init();   
    }

    public GenericDAO(int primaryKeyValue) {
        this.init();
        if (primaryKeyValue > 0)
            values.put(primaryKey, primaryKeyValue);
    }

    /**
     * Prepara a query antes de ser executada, as subclasses implementam suas preparações
     */
    public GenericDAO prepare() {
        return this;
    }

    /**
     * Inicializa e normaliza os atributos da classe
     */
    private void init() {      
        subClass = (Class<I>) this.getClass();
        
        try {
            Method method = this.subClass.getMethod("tableProperties");
            TableProperties result = (TableProperties) method.invoke(this);
            this.table = result.getTable();
            this.columns = result.getColumns();
            this.primaryKey = result.getPrimaryKey();
            this.foreignKeys = result.getForeignKeys();
        } catch(Exception ex) {
            Alerts.error(null, "Erro ao executar a ação", String.format("%s", ex));
        }
                
        if (primaryKey.length() > 0) {
            columns = insertIfNotExists(columns, primaryKey);
        }
        
        for (String column : foreignKeys)
            columns = insertIfNotExists(columns, column);

        for (String column : columns)
            values.put(column, new None());
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public boolean hasValue(String key) {
        return values.containsKey(key);
    }
    
    /**
     * Retorna o valor de uma coluna
     * @param coluna
     * @param target
     * @return
     */
    public <T> T getValue(String coluna, Class<T> target) {
        return target.cast(values.get(coluna));
    }

    public Object getValue(String coluna) {
        return values.get(coluna);
    }

    /**
     * @return
     */
    public HashMap<String, Object> getValues() {
        return values;
    }

    /**
     * Define o valor de uma coluna
     * @param coluna
     * @param valor
     */
    public void setValue(String coluna, Object valor) {
        values.put(coluna, valor);
    }

    /**
     * @return Retorna apenas os values validos/setados
     */
    private ValidValues validValues() {
        ValidValues validValues = new ValidValues();

        for (String coluna : columns) {
            if (coluna == primaryKey || values.get(coluna) instanceof None)
                continue;

            validValues.add(coluna, values.get(coluna));
        }

        return validValues;
    }

    /**
     * Define o where com base nos valores da chave primaria ou das chaves estrangeiras
     */
    protected GenericDAO defineWhere() {
        if (primaryKey.length() > 0) {
            this.where(primaryKey, values.get(primaryKey));
        } else {
            this.where((query) -> {
                for (int i = 0; i < foreignKeys.length; i++) {
                    if (i == 0)
                        query.where(foreignKeys[0], values.get(foreignKeys[0]));
                    else
                        query.and(foreignKeys[i], values.get(foreignKeys[i]));
                }
            });
        }
        return this;
    }

    /**
     * Insere um novo registro e salva a primary key caso exista
     */
    private boolean _insert() throws Exception {
        boolean inserted = false;
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;

        statement = super.insert(connection, validValues());
        inserted = statement.executeUpdate() > 0;
        if (inserted && primaryKey.length() > 0) {
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next())
                    values.put(primaryKey, rs.getInt(1));
            }
        }

        return inserted;
    }

    /**
     * Atualiza o registro
     */
    private boolean _update() throws Exception {
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;
        statement = this.defineWhere().update(connection, validValues());
        return statement.executeUpdate() > 0;
    }

    /**
     * @param action
     */
    public boolean save(SaveAction action) {
        boolean saved = false;
        try {
            if (action == SaveAction.DEFAULT) {
                saved = exists() ? _update() : _insert();
            }
            else if (action == SaveAction.FORCE_INSERT) {
                saved = _insert();
            }
            else if (action == SaveAction.FORCE_UPDATE) {
                saved = _update();
            }
        } catch (Exception ex) {
            Alerts.errorLog("Erro ao executar a ação", ex);
        }

        this.reset();
        return saved;
    }

    /**
     * Cria ou atualiza um registro com base na chave primária
     */
    public boolean save() throws Exception {
        return this.save(SaveAction.DEFAULT);
    }

    /**
     * Deleta o registro
     * @return
     */
    public boolean delete() {
        boolean result = false;
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;

        try {
            statement = this.defineWhere().delete(connection);
            result = statement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Alerts.errorLog("Erro ao executar a ação", ex);

        } finally {
            Conexao.fecharConexao(connection, statement);
        }

        return result;
    }

    /**
     * Procura um registro pelo valor da chave primária
     * @return
     */
    public boolean find() {
        boolean exists = true;
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;

        try {
            this.select("*");
            this.defineWhere();
            statement = super.get(connection);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                for (String column : columns) {
                    values.put(column, result.getObject(column));
                }
            } else {
                exists = false;
            }
        } catch (Exception ex) {
            Alerts.errorLog("Erro ao executar a ação", ex);

        } finally {
            Conexao.fecharConexao(connection, statement);
        }

        return exists;
    }

    /**
     * Obtém os dados do registro
     */
    public ArrayList<I> get() {
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;
        ArrayList<I> results = new ArrayList<>();

        try {
            statement = super.get(connection);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    I resultado = subClass.getDeclaredConstructor().newInstance();
                    ResultSetMetaData metaData = result.getMetaData();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        String column = metaData.getColumnLabel(i);
                        resultado.setValue(column, result.getObject(column));
                    }
                    results.add(resultado);
                }
            }
        } catch (Exception ex) {
            Alerts.errorLog("Erro ao executar a ação", ex);

        } finally {
            Conexao.fecharConexao(connection, statement);
        }
        
        return results;
    }

    /**
     * Esse registro existe na tabela?
     * Verifica a chave primária, caso não exista, tenta pelas estrangeiras
     * @return
     */
    public boolean exists() {
        boolean exists = false;
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;

        try {
            if (primaryKey.length() > 0) {
                if (this.values.get(primaryKey) instanceof None ||
                        this.<Integer>getValue(primaryKey, Integer.class) <= 0) {
                    Conexao.fecharConexao(connection);
                    return false;
                }
            }

            this.select("1");
            this.defineWhere();
            statement = super.get(Conexao.getConexao());
            try (ResultSet result = statement.executeQuery()) {
                exists = result.next();
            }
        } catch (Exception ex) {
            Alerts.errorLog("Erro ao executar a ação", ex);

        } finally {
            Conexao.fecharConexao(connection, statement);
        }

        return exists;
    }

    /**
     * @param query
     * @param values
     * @return
     * @throws SQLException
     */
    public static PreparedStatement prepareQuery(Connection connection, String query, Object... values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
        return statement;
    }
    
    /**
     * DAO pra Bean
     * @return 
     */
    public Object toBean() {
        return null;
    }

    /**
     * Cria um novo DAO com os dados do Bean
     * @param t
     * @return 
     */
    public static Object fromBean(Object t) {
        return null;
    }
}
