package hu.me.fdsz.model.enums;

import lombok.Getter;


public enum RoomType {

    THREE_BED(3), FOUR_BED(4);

    @Getter
    private Integer value;

    RoomType(Integer value) {
        this.value = value;
    }

}
