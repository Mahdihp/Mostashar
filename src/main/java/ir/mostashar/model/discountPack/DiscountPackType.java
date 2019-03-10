package ir.mostashar.model.discountPack;

public enum DiscountPackType {

    LAWYER(0),
    CLIENT(1),
    ALL(2);

    public final int type;

    private DiscountPackType(int type) {
        this.type = type;
    }
}
