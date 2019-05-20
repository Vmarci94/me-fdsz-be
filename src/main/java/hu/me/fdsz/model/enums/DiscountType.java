package hu.me.fdsz.model.enums;

public enum DiscountType {

    NOTHING(1d), COUSIN(0.2d), FDSZ_MEMBER(0.4d), ME_WORKER(0.5d);

    private double value;

    DiscountType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
