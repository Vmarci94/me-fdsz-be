package hu.me.fdsz.model.enums;

public enum RoomType {

    THREE_BED(3), FOUR_BED(4);

    private int value;

    RoomType(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " type of room";
    }

}
