package hu.me.fdsz.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue
    private long id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(DATE)
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @Temporal(DATE)
    private Date lastModifiedDate;

}
