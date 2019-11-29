package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.TemporalType.DATE;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Booking extends BaseEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    private User author;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id", nullable = false)
    private User lastModifiedBy;

    @Column(name = "booking_date", nullable = false)
    @Temporal(DATE)
    private Date bookingDate;

    @Column(name = "number_of_nights", nullable = false)
    private int numberOfNights;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "booking_room",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")}
    )
    @MapKey(name = "roomNumber")
    private Map<Long, Room> rooms = new HashMap<>();

//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private List<Room> rooms;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "booking_guest",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "guest_id")}
    )
    private List<Guest> guests = new ArrayList<>();

}
