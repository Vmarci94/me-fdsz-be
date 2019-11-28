package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price")
    private Long price;

    @ManyToMany(mappedBy = "rooms")
    private Set<Turnus> turnusSet;

}
