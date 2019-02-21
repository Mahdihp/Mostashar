package ir.mostashar.model.bill.dto;

public class BillDTOBuilder {
    private String uid;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String status;
    private Long value;
    private String orgUid;
    private String walletUid;
    private String factorUid;

    public BillDTOBuilder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public BillDTOBuilder setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public BillDTOBuilder setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public BillDTOBuilder setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public BillDTOBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public BillDTOBuilder setValue(Long value) {
        this.value = value;
        return this;
    }

    public BillDTOBuilder setOrgUid(String orgUid) {
        this.orgUid = orgUid;
        return this;
    }

    public BillDTOBuilder setWalletUid(String walletUid) {
        this.walletUid = walletUid;
        return this;
    }

    public BillDTOBuilder setFactorUid(String factorUid) {
        this.factorUid = factorUid;
        return this;
    }

    public BillDTO createBillDTO() {
        return new BillDTO(uid, transactionNumber, trackingNumber, transactionDate, status, value, orgUid, walletUid, factorUid);
    }
}