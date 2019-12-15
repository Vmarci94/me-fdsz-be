package hu.me.fdsz.model.dto;

import lombok.*;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TurnusDTO {

    private Long id;

    @Temporal(DATE)
    private Date startDate;

    @Temporal(DATE)
    private Date endDate;

    private Boolean enabled;

    private List<RoomDTO> rooms;

    private Boolean deletable;

}
