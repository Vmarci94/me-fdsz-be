package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

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

//    @OneToMany()
//    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
//    private List<Room> roomList;

    @Column(name = "booking_name", nullable = false)
    @Temporal(DATE)
    private Date bookingDate;

    @Column(name = "number_of_nights", nullable = false)
    private int numberOfNights;

}
