package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "roomNumber")
@ToString(of = "roomNumber")
public class Room implements Serializable {

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price")
    private Long price;

    @OneToMany
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    private List<Reservation> reservations;

}
