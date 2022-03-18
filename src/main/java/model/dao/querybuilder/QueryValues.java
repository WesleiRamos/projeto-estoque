package model.dao.querybuilder;

import java.util.ArrayList;

public class QueryValues {
    private ArrayList<String> query = new ArrayList<>();
    private ArrayList<Object> values = new ArrayList<>();

    public QueryValues() {}

    public QueryValues(ArrayList<String> query, ArrayList<Object> values) {
        this.query = query;
        this.values = values;
    }

    public void add(String query) {
        this.query.add(query);
    }

    public void add(String query, Object value) {
        this.query.add(query);
        this.values.add(value);
    }

    public void concat(QueryValues queryValues) {
        this.query.addAll(queryValues.getQuery());
        this.values.addAll(queryValues.getValues());
    }

    public void addValue(Object value) {
        this.values.add(value);
    }

    public int size() {
        return values.size();
    }

    public Object getValue(int index) {
        return values.get(index);
    }

    public ArrayList<String> getQuery() {
        return query;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public String toString() {
        return String.join(" ", query);
    }
}
