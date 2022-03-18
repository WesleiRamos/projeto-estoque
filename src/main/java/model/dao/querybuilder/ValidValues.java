package model.dao.querybuilder;

import java.util.ArrayList;

/**
 * Classe que guarda dados válidos informados
 */
public class ValidValues {
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Object> values = new ArrayList<>();

    /**
     * Adiciona um par chave-valor
     * @param chave
     * @param valor
     */
    public void add(String chave, Object valor) {
        keys.add(chave);
        values.add(valor);
    }

    /**
     * Retorna o número de pares chave-valor
     * @return
     */
    public int size() {
        return keys.size();
    }

    /**
     * Retorna a chave de um par
     * @param index
     * @return
     */
    public Object getValue(int index) {
        return values.get(index);
    }

    /**
     * Retorna a chave de um par
     * @param index
     * @return
     */
    public String getKey(int index) {
        return keys.get(index);
    }

    /**
     * Retorna os valores
     * @return
     */
    public ArrayList<Object> getValues() {
        return values;
    }

    /**
     * Retorna as chaves
     * @return
     */
    public ArrayList<String> getKeys() {
        return keys;
    }

    /**
     * Retorna as chaves no formato de inserção
     * @return
     */
    public String getAsInsertFields() {
        return String.format("(%s)", String.join(", ", keys));
    }

    /**
     * Retorna os valores no formato de inserção
     * @return
     */
    public String getAsInsertValues() {
        ArrayList<String> list = new ArrayList<>();
        
        for (int i = 0; i < values.size(); i++) {
            Object value = values.get(i);
            if (value instanceof Raw) {
                list.add(((Raw) value).getRawString());
            } else {
                list.add("?");
            }
        }

        return String.format("(%s)", String.join(", ", list));
    }

    /**
     * Retorna as chaves no formato de atualização
     * @return
     */
    public String getAsUpdate() {
        ArrayList<String> fields = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            Object value = values.get(i);
            if (value instanceof Raw) {
                fields.add(String.format("%s = %s", keys.get(i), value));
            } else {
                fields.add(String.format("%s = ?", keys.get(i)));
            }
        }
        return String.join(", ", fields);
    }
}