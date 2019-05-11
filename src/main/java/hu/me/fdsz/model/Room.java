package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "roomNumber")
@ToString(of = "roomNumber")
public class Room {

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price")
    private Long price;
//
//    @ManyToMany(mappedBy = "rooms")
//    private Set<Turnus> turnusList;

}
