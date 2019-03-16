package ir.mostashar.model.billwallettransaction;

public enum TransactionType {

    BY_DESCRIPTION_DEPOSIT(1),    //  حاوی توضیحات (به وسیله ادمین-سهم مشاور-خرید بسته) (واریز)
    BY_ADVICE_DEPOSIT(0),         //  تراکنش مالی (انجام خدمت-کد درخواست)(واریز)

    BY_DESCRIPTION_WITHDRAW(3),   //  حاوی توضیحات (برداشت)
    BY_ADVICE_WITHDRAW(2);        //  مشاوره تلفنی(شماره فاکتور و کد درخواست) (برداشت)


    public final int type;

    TransactionType(int type) {
        this.type = type;
    }
}
