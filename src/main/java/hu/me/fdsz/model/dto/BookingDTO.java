package hu.me.fdsz.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookingDTO {

    private Long id;

    private UserDTO author;

    private List<RoomDTO> roomList;

    private List<GuestDTO> guests;

    private TurnusDTO turnusDTO;

    private int numberOfNights;

}
