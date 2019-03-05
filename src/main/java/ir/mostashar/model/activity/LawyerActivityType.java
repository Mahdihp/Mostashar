package ir.mostashar.model.activity;

public enum LawyerActivityType {

    ADD_COMMENT(0), // setter type 9
    READ_COMMENT(1), // setter type 10
    ADD_FILE(2),
    READ_DOC(3), // setter type 11
    DWONLOAD_DOC(4), // setter type 12
    DELETE_DOC(5); // setter type 13

    public final int type;

    private LawyerActivityType(int type) {
        this.type = type;
    }
}
