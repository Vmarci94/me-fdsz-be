package hu.me.fdsz.dto;

import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {

    private long roomNumber;

    private RoomType roomType;

    private Long price;

    private User roomOwner;

}
