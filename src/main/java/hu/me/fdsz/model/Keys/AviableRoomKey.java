package hu.me.fdsz.model.Keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class AviableRoomKey implements Serializable {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "rooom_number")
    private Long roomNumber;

}
