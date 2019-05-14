package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Turnus implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "start_date", unique = true, nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", unique = true, nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean enabled;

}
