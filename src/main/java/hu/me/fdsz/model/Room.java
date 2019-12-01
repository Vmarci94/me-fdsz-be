package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Room extends BaseEntity {

    @Column(name = "room_number", nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private long roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private long price;

    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    private List<Turnus> turnus;

//    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
//    private List<Booking> bookingList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

}
