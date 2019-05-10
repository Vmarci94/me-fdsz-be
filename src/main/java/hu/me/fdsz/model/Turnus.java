package hu.me.fdsz.model;

import hu.me.fdsz.model.key.TurnusKey;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Turnus {

    @EmbeddedId
    private TurnusKey id;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Long numberOfDays;

//    @OneToMany
//    @JoinColumns({
//            @JoinColumn(name = "year", referencedColumnName = "year"),
//            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
//    })
//    private List<Reservation> reservationList;

    @Column(nullable = false)
    private boolean full;


}
