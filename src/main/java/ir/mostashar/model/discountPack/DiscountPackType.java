package ir.mostashar.model.discountPack;

public enum DiscountPackType {

    LAWYER(0),
    CLIENT(1),
    SPECIALE(2),
    ALL(3);

    public final int type;

    private DiscountPackType(int type) {
        this.type = type;
    }
}
