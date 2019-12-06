package hu.me.fdsz.dto;

import hu.me.fdsz.model.enums.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {

    private Long roomNumber;

    private RoomType roomType;

    private Long price;

    private boolean available;

    private Boolean selected;

}
