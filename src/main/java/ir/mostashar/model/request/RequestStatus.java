package ir.mostashar.model.request;

public enum RequestStatus {

    SELECT_LAWYER, //  در انتظار انتخاب مشاور
    WAIT_PEYMENT,// در انتظار خرید بسته
    WAIT_CALL, // در انتظار تماس از طرف مشاور
    CANCELED_BY_LAWYER, // کنسل شدن از طرف مشاور
    CANCELED_BY_CLIENT, // کنسل شدن از طرف کاربر
    DONE_BY_LAWYER, // پرونده تمام شده و مختومه شده
    DONE_BY_CLIENT // پرونده تمام شده و مختومه شده
}
