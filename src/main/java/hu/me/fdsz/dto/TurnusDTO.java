package hu.me.fdsz.dto;

import hu.me.fdsz.model.Room;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private long turnusNumber;

    private long turnusYear;

    private Date startDate;

    private Date endDate;

    private int numberOfDays;

    private List<Room> roomList;

    private boolean full;

}
