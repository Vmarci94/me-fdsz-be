package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    private Turnus turnus;

    @ManyToOne
    @JoinColumn(name = "room_owner", referencedColumnName = "id")
    private User roomOwner;


}
