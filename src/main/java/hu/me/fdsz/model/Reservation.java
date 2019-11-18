package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Reservation extends BaseEntity {

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "room_number", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "room_owner", referencedColumnName = "id", nullable = false)
    private User roomOwner;


}
