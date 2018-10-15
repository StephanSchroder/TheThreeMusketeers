/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Nico
 */
public class DataTablesCollection {
    private String mainTable;
    private String joiningTable;
    private String mainTableColumn;
    private String joiningTableColumn;
    private String joinType;

    public DataTablesCollection(String mainTable) {
        this.mainTable = mainTable;
        this.joiningTable = "";
        this.mainTableColumn = "";
        this.joiningTableColumn = "";
        this.joinType = "";
    }

    public DataTablesCollection(String mainTable, String joiningTable, String mainTableColumn, String joiningTableColumn, String joinType) {
        this.mainTable = mainTable;
        this.joiningTable = joiningTable;
        this.mainTableColumn = mainTableColumn;
        this.joiningTableColumn = joiningTableColumn;
        this.joinType = joinType;
    }

    public String getMainTable() {
        return mainTable;
    }

    public void setMainTable(String mainTable) {
        this.mainTable = mainTable;
    }

    public String getJoiningTable() {
        return joiningTable;
    }

    public void setJoiningTable(String joiningTable) {
        this.joiningTable = joiningTable;
    }

    public String getMainTableColumn() {
        return mainTableColumn;
    }

    public void setMainTableColumn(String mainTableColumn) {
        this.mainTableColumn = mainTableColumn;
    }

    public String getJoiningTableColumn() {
        return joiningTableColumn;
    }

    public void setJoiningTableColumn(String joiningTableColumn) {
        this.joiningTableColumn = joiningTableColumn;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }
}