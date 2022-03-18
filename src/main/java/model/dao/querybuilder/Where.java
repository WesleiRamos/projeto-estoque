package model.dao.querybuilder;

import java.util.ArrayList;

class WhereClause {
    private String field;
    private String symbol;
    private Object value;
    private String op = "";
    private Raw raw;

    public WhereClause(Raw raw) {
        this.raw = raw;
    }

    public WhereClause(String field, String symbol, Object value, String op) {
        this.field = field;
        this.symbol = symbol;
        this.value = value;
        this.op = op;
    }

    public String getClause() {
        return isRaw()
            ? raw.getRawString()
            : String.format("%s %s %s ?", op, field, symbol).trim();
    }

    public Object getValue() {
        return isRaw()
                ? raw.getRawValues()
                : value;
    }

    public boolean isRaw() {
        return raw != null;
    }
}

public class Where {
    private ArrayList<WhereClause> whereClauses = new ArrayList<>();

    public void reset() {
        this.whereClauses.clear();
    }

    private Where add(String field1, String symbol, Object value, String op) {
        if (this.whereClauses.size() == 0 && op != "")
            throw new IllegalArgumentException(String.format("%s não pode ser usado como primeiro operador", op));

        switch (symbol) {
            case "=":
            case "!=":
            case ">":
            case "<":
            case ">=":
            case "<=":
                this.whereClauses.add(new WhereClause(field1, symbol, value, op));
                break;
            default:
                throw new IllegalArgumentException("Operador de comparação inválido: " + symbol);
        }
        return this;
    }

    public Where where(String field, Object value) {
        return this.add(field, "=", value, "");
    }

    public Where where(String field, String symbol, Object value) {
        return this.add(field, symbol, value, "");
    }

    public Where or(String field, Object value) {
        return this.add(field, "=", value, "OR");
    }

    public Where or(String field, String symbol, Object value) {
        return this.add(field, symbol, value, "OR");
    }

    public Where and(String field, Object value) {
        return this.add(field, "=", value, "AND");
    }

    public Where and(String field, String symbol, Object value) {
        return this.add(field, symbol, value, "AND");
    }

    public Where not(String field, Object value) {
        return this.add(field, "=", value, "NOT");
    }

    public Where not(String field, String symbol, Object value) {
        return this.add(field, symbol, value, "NOT");
    }

    public Where raw(String raw) {
        this.whereClauses.add(new WhereClause(QueryBuilder.raw(raw)));
        return this;
    }

    public Where raw(String raw, Object... values) {
        this.whereClauses.add(new WhereClause(QueryBuilder.raw(raw.trim(), values)));
        return this;
    }

    public QueryValues getQuery() {
        ArrayList<Object> values = new ArrayList<>();
        ArrayList<String> clauses = new ArrayList<>();
        if (this.whereClauses.size() > 0) {
            clauses.add("WHERE");
            for (WhereClause clause : this.whereClauses) {
                clauses.add(clause.getClause());
                if (clause.isRaw()) {
                    Object[] rawValues = (Object[]) clause.getValue();
                    for (Object rawValue : rawValues) {
                        values.add(rawValue);
                    }
                } else {
                    values.add(clause.getValue());
                }
            }
        }
        return new QueryValues(clauses, values);
    }
}