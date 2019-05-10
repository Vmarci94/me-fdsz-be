package hu.me.fdsz.dto;

import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ReservationDTO {

    private Long id;

    private Turnus turnus;

    private List<Room> roomList;

    private User owner;
}
