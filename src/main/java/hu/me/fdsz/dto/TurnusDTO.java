package hu.me.fdsz.dto;

import hu.me.fdsz.model.Room;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer numberOfDays;

    private List<Room> rooms;

    private Boolean full;

    private Boolean enabled;

}
