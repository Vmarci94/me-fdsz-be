package hu.me.fdsz.dto;

import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.key.TurnusKey;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private TurnusKey turnusKey;

    private LocalDate endDate;

    private int numberOfDays;

    private Collection<Room> roomList;

    private boolean full;

}
