package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import java.util.Date;

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

    @Column(nullable = false)
    private boolean enabled;

}
