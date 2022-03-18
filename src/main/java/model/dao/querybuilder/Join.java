package model.dao.querybuilder;

import java.util.ArrayList;
import java.util.Locale;

class JoinField {
    private String op;
    private String field1;
    private String field2;
    private String symbol;

    public JoinField(String op, String field1, String field2, String symbol) {
        this.op = op;
        this.field1 = field1;
        this.field2 = field2;
        this.symbol = symbol;
    }

    public String toString() {
        return String.format("%s %s %s %s", op, field1, symbol, field2);
    }
}

public class Join {
    private String table;
    private String type = "JOIN";
    private ArrayList<JoinField> joins = new ArrayList<>();

    public Join(String table) {
        this.table = table;
    }

    public Join(String table, String type) {
        this.table = table;
        this.type = String.format("%s %s", type, this.type).toUpperCase(Locale.ROOT);
    }

    public void add(String logOp, String field1, String symbol, String field2) {
        this.joins.add(new JoinField(logOp, field1, field2, symbol));
    }

    public Join on(String field1, String symbol, String field2) {
        this.add("ON", field1, symbol, field2);
        return this;
    }

    public Join or(String field1, String symbol, String field2) {
        if (this.joins.size() == 0)
            throw new IllegalArgumentException("OR só pode ser usado após ON");

        this.add("OR", field1, symbol, field2);
        return this;
    }

    public Join and(String field1, String symbol, String field2) {
        if (this.joins.size() == 0)
            throw new IllegalArgumentException("OR só pode ser usado após ON");

        this.add("AND", field1, symbol, field2);
        return this;
    }

    public String toString() {
        ArrayList<String> comparisons = new ArrayList<>();
        for (JoinField join : this.joins)
            comparisons.add(join.toString());

        return String.format("%s %s %s", type.trim(), table, String.join(" ", comparisons));
    }
}