package ir.mostashar.model.activity;

public enum LawyerActivityType {

    ADD_COMMENT(0), // setter typeUser 9
    READ_COMMENT(1), // setter typeUser 10
    ADD_FILE(2),
    READ_DOC(3), // setter typeUser 11
    DWONLOAD_DOC(4), // setter typeUser 12
    DELETE_DOC(5); // setter typeUser 13

    public final int type;

    private LawyerActivityType(int type) {
        this.type = type;
    }
}
