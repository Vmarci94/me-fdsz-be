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
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Turnus extends BaseEntity {

    @Column(name = "start_date", unique = true, nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", unique = true, nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean enabled;

}
