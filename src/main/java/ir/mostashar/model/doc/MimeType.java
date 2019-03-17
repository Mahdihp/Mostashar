package ir.mostashar.model.doc;

public enum MimeType {
    AUDIO(0),
    VIDEO(1),
    PDF(2),
    PICTURE(3),
    TEXT(4),
    ZIP_FILE(5),
    RAR_FILE(6);
    public final int type;

    MimeType(int type) {
        this.type = type;
    }
}
