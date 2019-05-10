package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "roomNumber")
@ToString(of = "roomNumber")
public class Room {

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "number_of_beds", nullable = false)
    private int numberOfBeds;

    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price")
    private Long price;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_id", referencedColumnName = "room_number")
//    private List<Reservation> reservationList;


}
