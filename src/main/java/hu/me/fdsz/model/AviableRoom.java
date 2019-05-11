package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AviableRoom {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "rooom_number")
    private Long roomNumber;

    @
    private List<Room> roomList

}
