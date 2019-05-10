package hu.me.fdsz.model.key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class TurnusKey implements Serializable {

    @Column(name = "year")
    private int year;

    @Column(name = "start_date")
    private LocalDate startDate;

}
