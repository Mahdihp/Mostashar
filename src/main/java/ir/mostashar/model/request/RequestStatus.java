package ir.mostashar.model.request;

public enum RequestStatus {

    SELECT_LAWYER, //  در انتظار انتخاب مشاور
    WAIT_PEYMENT,// در انتظار خرید بسته
    WAIT_CALL, // در انتظار تماس از طرف مشاور
    CANCELED, // کنسل شدن درخواست توسط کاربر
    DONE // پرونده تمام شده و مختومه شده
}
