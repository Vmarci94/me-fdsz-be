package hu.me.fdsz.dto;

import lombok.*;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.Map;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private long id;

    @Temporal(DATE)
    private Date startDate;

    @Temporal(DATE)
    private Date endDate;

    private boolean enabled;

    private Map<Long, RoomDTO> rooms;

}
