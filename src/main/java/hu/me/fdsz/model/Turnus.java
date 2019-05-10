package hu.me.fdsz.model;

import hu.me.fdsz.model.key.TurnusKey;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Turnus {

    @EmbeddedId
    private TurnusKey id;

    private Date startDate;

    private Date endDate;

    private Long numberOfDays;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "room_turnus",
            joinColumns = {@JoinColumn(name = "turnus_number"), @JoinColumn(name = "turnus_year")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")}
    )
    private List<Room> rooms;


}
