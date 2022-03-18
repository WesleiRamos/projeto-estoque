package model.dao.querybuilder;

public class TableProperties {
    private String table = "";
    private String[] columns = {};
    private String primaryKey = "";
    private String[] foreignKeys = {};
    
    public TableProperties() {}
    
    public TableProperties(String table) {
        this.table = table;
    }
    
    public TableProperties(String table, String[] columns) {
        this.table = table;
        this.columns = columns;
    }
    
    public TableProperties(String table, String[] columns, String primaryKey) {
        this.table = table;
        this.columns = columns;
        this.primaryKey = primaryKey;
    }
    
    public TableProperties(String table, String[] columns, String primaryKey, String[] foreignKeys) {
        this.table = table;
        this.columns = columns;
        this.primaryKey = primaryKey;
        this.foreignKeys = foreignKeys;
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @return the columns
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * @return the primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @return the foreignKeys
     */
    public String[] getForeignKeys() {
        return foreignKeys;
    }
}
