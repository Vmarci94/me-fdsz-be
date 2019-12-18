package hu.me.fdsz.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    @Column(name = "number_of_nights", nullable = false)
    private int numberOfNights;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Room.class, mappedBy = "booking")
    private List<Room> roomList;

}
