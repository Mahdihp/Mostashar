package ir.mostashar.model.bill;

public enum BillType {

    WITHDRAW(0), // برداشت از حساب
    DEPOSIT(1), // واریز به حساب
    CHECKOUT(2);// تسویه حساب

    public final int type;

    private BillType(int type) {
        this.type = type;
    }
}
