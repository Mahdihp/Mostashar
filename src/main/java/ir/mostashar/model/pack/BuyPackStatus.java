package ir.mostashar.model.pack;

import lombok.Data;

@Data
public class BuyPackStatus {

    private BuyPack buyPack;
    private String factorUid;
    private String consumptionPackUid;

    public BuyPackStatus() {
    }

    public BuyPackStatus(BuyPack buyPack) {
        this.buyPack = buyPack;
    }

    public BuyPackStatus(String factorUid, String consumptionPackUid, BuyPack buyPack) {
        this.buyPack = buyPack;
        this.factorUid = factorUid;
        this.consumptionPackUid = consumptionPackUid;
    }
}
