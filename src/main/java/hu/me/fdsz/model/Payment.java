package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Payment extends BaseEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    private User payerUser;

    @OneToOne
    @JoinColumn(name = "booing_id", referencedColumnName = "id", nullable = false)
    private Booking booking;

}
