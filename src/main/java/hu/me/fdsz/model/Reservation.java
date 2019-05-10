package hu.me.fdsz.model;

import hu.me.fdsz.model.key.TurnusKey;
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

    @Column(nullable = false)
    private TurnusKey turnusKey;

    @Column(name = "room_number", nullable = false)
    private long roomNumber;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_number")
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "year", referencedColumnName = "year"),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    })
    private Turnus turnus;

    @ManyToOne
    @JoinColumn(name = "room_owner", referencedColumnName = "id", nullable = false)
    private User roomOwner;

}
