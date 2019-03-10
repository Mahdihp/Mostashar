package ir.mostashar.model.adminConfirmation;

public enum TypeConfirmation {

    VERIFIED_LAWYER(0),
    VERIFIED_ABOUT_LAWYER(1),
    UPGRADE_RATING(2),
    VERIFIED_COMMENTS(3),
    VERIFIED_AVATAR_USER(4);



    public final int type;
    private TypeConfirmation(int type) {
        this.type = type;
    }
}
