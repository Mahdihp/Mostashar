package ir.mostashar.model.doc;

public enum DocType {
    Audio(0),
    Video(1),
    PDF(2),
    Picture(3),
    Text(4),
    ZipFile(5),
    RARFile(6);
    public final int type;

    private DocType(int type) {
        this.type = type;
    }
}
