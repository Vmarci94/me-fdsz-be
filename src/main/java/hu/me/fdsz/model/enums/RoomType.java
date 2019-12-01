package hu.me.fdsz.model.enums;

public enum RoomType {

    THREE_BED(3), FOUR_BED(4);

    private Integer value;

    RoomType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
