package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.DATE;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Turnus extends BaseEntity {

    @Column(name = "start_date", unique = true, nullable = false)
    @Temporal(DATE)
    private Date startDate;

    @Column(name = "end_date", unique = true, nullable = false)
    @Temporal(DATE)
    private Date endDate;

    @Type(type = "yes_no")
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "turnus_room",
            joinColumns = {@JoinColumn(name = "turnusId")},
            inverseJoinColumns = {@JoinColumn(name = "room_number")}
    )
    private Set<Room> rooms;

}
