package model.dao.querybuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.stream.Stream;

class Raw {
    private String rawString = "";
    private Object[] rawValues = {};

    public Raw(String rawString) {
        this.rawString = escapeStringForMySQL(rawString);
    }

    public Raw(String rawString, Object[] rawValues) {
        this.rawString = escapeStringForMySQL(rawString);
        this.rawValues = rawValues;
    }

    public String getRawString() {
        return rawString;
    }

    public Object[] getRawValues() {
        return rawValues;
    }
    
    public String toString() {
        return rawString;
    }

    /**
     * https://stackoverflow.com/a/45294943/5057104
     * @param s
     * @return
     */
    public static String escapeStringForMySQL(String s) {
        return s.replaceAll("\\\\", "\\\\\\\\")
            .replaceAll("\b", "\\b")
            .replaceAll("\n", "\\n")
            .replaceAll("\r", "\\r")
            .replaceAll("\t", "\\t")
            .replaceAll("\\x1A", "\\Z")
            .replaceAll("\\x00", "\\0")
            .replaceAll("'", "\\'")
            .replaceAll("\"", "\\\"")
            .replaceAll("%", "\\%")
            .replaceAll("_", "\\_");
    }
}

abstract public class QueryBuilder {
    protected String table = "";
    protected String[] columns = {};
    protected String primaryKey = "";
    protected String[] foreignKeys = {};

    private int limitResults = 0;
    private String[] orderBy = {};
    private String[] selectFields = {};
    private final Where whereClauses = new Where();
    private final ArrayList<Join> joins = new ArrayList<>();

    /**
     * Adiciona um elemento ao array caso não exista
     * @param array
     * @param value
     * @return
     */
    protected static String[] insertIfNotExists(String[] array, String value) {
        Stream<String> contains = Arrays.stream(array);
        if (contains.anyMatch(value::equals))
            return array;

        return Stream.concat(Arrays.stream(array), Stream.of(value)).toArray(String[]::new);
    }

    public void reset() {
        this.limitResults = 0;
        this.orderBy = new String[] {};
        this.selectFields = new String[]{};
        this.whereClauses.reset();
        this.joins.clear();
    }

    private Join createJoin(String table, String type) {
        Join newJoin = type.equals("") ? new Join(table) : new Join(table, type);
        joins.add(newJoin);
        return newJoin;
    }

    /**
     * Gera as clausulas WHERE
     * @param callback
     * @return
     */
    public QueryBuilder where(WhereCallback callback) {
        callback.callback(this.whereClauses);
        return this;
    }

    public QueryBuilder where(String column, Object value) {
        this.whereClauses.where(column, value);
        return this;
    }

    public QueryBuilder where(String column, String operator, Object value) {
        this.whereClauses.where(column, operator, value);
        return this;
    }

    /** JOINS **/
    public QueryBuilder join(String table, JoinCallback callback) {
        callback.callback(createJoin(table, ""));
        return this;
    }

    public QueryBuilder leftJoin(String table, JoinCallback callback) {
        callback.callback(createJoin(table, "LEFT"));
        return this;
    }

    public QueryBuilder rightJoin(String table, JoinCallback callback) {
        callback.callback(createJoin(table, "RIGHT"));
        return this;
    }

    public QueryBuilder innerJoin(String table, JoinCallback callback) {
        callback.callback(createJoin(table, "INNER"));
        return this;
    }

    public QueryBuilder fullJoin(String table, JoinCallback callback) {
        callback.callback(createJoin(table, "FULL"));
        return this;
    }

    public QueryBuilder crossJoin(String table, JoinCallback callback) {
        callback.callback(createJoin(table, "CROSS"));
        return this;
    }

    /** Selects **/

    public QueryBuilder select() {
        this.selectFields = this.columns.clone();
        return this;
    }

    public QueryBuilder select(String... columns) {
        if (columns.length == 1) {
            return this.select();
        }
        this.selectFields = columns;
        return this;
    }

    public QueryBuilder selectRaw(String raw) {
        this.selectFields = new String[] { Raw.escapeStringForMySQL(raw) };
        return this;
    }

    /** LIMIT, ORDER BY **/
    public QueryBuilder limit(int limitResults) {
        this.limitResults = limitResults;
        return this;
    }

    public QueryBuilder orderBy(String column) {
        return this.orderBy(column, "DESC");
    }

    public QueryBuilder orderBy(String column, String order) {
        switch (order.toUpperCase()) {
            case "ASC":
            case "DESC":
                this.orderBy = new String[] { column, order };
                break;
            default:
                throw new IllegalArgumentException("Ordem inválida " + order);
        }
        return this;
    }

    /**
     * Retorna os joins informados
     * @return
     */
    private String getJoins() {
        ArrayList<String> joinStrings = new ArrayList<>();
        for (Join join : joins)
            joinStrings.add(join.toString());
        return String.join(" ", joinStrings);
    }

    /**
     * Prepara a query select para ser gerada
     * @return
     */
    private QueryValues prepareQuerySelect() {
        ArrayList<String> query = new ArrayList<>();
        query.add("SELECT");
        if (primaryKey.length() > 0) {
            query.add(String.join(", ", insertIfNotExists(this.selectFields, primaryKey)));
        } else {
            query.add(String.join(", ", this.selectFields));
        }
        query.add("FROM");
        query.add(table);
        if (this.joins.size() > 0)
            query.add(this.getJoins());

        QueryValues where = this.whereClauses.getQuery();
        query.add(where.toString());
        return new QueryValues(query, where.getValues());
    }

    /**
     * Prepara uma query de selecionar, pode retornar um resultado ou vários
     * @param connection
     * @return
     * @throws SQLException
     */
    protected PreparedStatement get(Connection connection) throws SQLException {
        if (this.selectFields.length == 0)
            this.select();

        QueryValues query = this.prepareQuerySelect();
        if (this.orderBy.length > 0) {
            query.add("ORDER BY");
            query.add(this.orderBy[0]);
            query.add(this.orderBy[1]);
        }

        if (this.limitResults > 0) {
            query.add(String.format("LIMIT %d", this.limitResults));
        }

        PreparedStatement statement = connection.prepareStatement(query.toString());
        for (int i = 0; i < query.size(); i++)
            statement.setObject(i + 1, query.getValue(i));

        System.out.println(statement);

        this.reset();
        return statement;
    }

    /**
     * Prepara uma query de atulizar
     * @param connection
     * @param values
     * @return
     * @throws SQLException
     */
    protected PreparedStatement update(Connection connection, ValidValues values) throws SQLException {
        QueryValues query = new QueryValues();
        query.add("UPDATE");
        query.add(table);
        query.add("SET");
        query.add(values.getAsUpdate());
        query.concat(this.whereClauses.getQuery());

        int inserted = 1;
        PreparedStatement statement = connection.prepareStatement(query.toString());
        for (int i = 0; i < values.size() + query.size(); i++) {
            if (i < values.size()) {
                statement.setObject(inserted++, values.getValue(i));
            }
            else {
                statement.setObject(inserted++, query.getValue(i - values.size()));
            }
        }

        this.reset();
        return statement;
    }

    /**
     * Prepara uma query de inserir
     * @param connection
     * @param values
     * @return
     * @throws SQLException
     */
    protected PreparedStatement insert(Connection connection, ValidValues values) throws SQLException {
        QueryValues query = new QueryValues();
        query.add("INSERT INTO");
        query.add(table);
        query.add(values.getAsInsertFields());
        query.add("VALUES");
        query.add(values.getAsInsertValues());

        int inserted = 1;
        PreparedStatement statement = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.size(); i++) {
            Object value = values.getValue(i);
            if (value instanceof Raw)
                continue;
            
            statement.setObject(inserted++, value);
        }
        
        this.reset();
        return statement;
    }

    /**
     * Prepara uma query de deletar
     * @param connection
     * @return
     * @throws SQLException
     */
    protected PreparedStatement delete(Connection connection) throws SQLException {
        QueryValues query = new QueryValues();
        query.add("DELETE FROM");
        query.add(table);
        query.concat(this.whereClauses.getQuery());

        PreparedStatement statement = connection.prepareStatement(query.toString());
        for (int i = 0; i < query.size(); i++)
            statement.setObject(i + 1, query.getValue(i));

        this.reset();
        return statement;
    }

    /**
     * @param query
     * @return
     */
    public static Raw raw(String query) {
        return new Raw(query);
    }

    public static Raw raw(String query, Object[] values) {
        return new Raw(query, values);
    }
}
