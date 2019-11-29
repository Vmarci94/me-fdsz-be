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

    @Column(name = "room_number", unique = true, nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private long roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private long price;

    @ManyToMany(mappedBy = "rooms")
    private List<Turnus> turnusSet;

    @ManyToMany(mappedBy = "rooms")
    private List<Booking> bookingList;

}
