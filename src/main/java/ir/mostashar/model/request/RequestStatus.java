package ir.mostashar.model.request;

public enum RequestStatus {

    SELECT_LAWYER(0), //  در انتظار انتخاب مشاور
    WAIT_PEYMENT(1),// در انتظار خرید بسته
    WAIT_CALL(2), // در انتظار تماس از طرف مشاور
    CANCELED_BY_LAWYER(3), // کنسل شدن از طرف مشاور
    CANCELED_BY_CLIENT(4), // کنسل شدن از طرف کاربر
    DONE_BY_LAWYER(5), // پرونده تمام شده و مختومه شده
    DONE_BY_CLIENT(6); // پرونده تمام شده و مختومه شده

    public final int type;

    RequestStatus(int type) {
        this.type = type;

    }
}
