package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Reservation {

    @Id
    @GeneratedValue
    private long id;

    private LocalDate reservationStartDate;

    private LocalDate reservationEndDate;

    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "room_owner", referencedColumnName = "id")
    private User roomOwner;


}
