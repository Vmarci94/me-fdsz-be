package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Turnus {

    @Id
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany
    @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    private List<Reservation> reservation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "aviable_room",
            joinColumns = {@JoinColumn(name = "start_date")},
            inverseJoinColumns = {@JoinColumn(name = "room_number")}
    )
    private List<Room> rooms;


}
