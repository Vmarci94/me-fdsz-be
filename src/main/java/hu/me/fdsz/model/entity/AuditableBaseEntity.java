package hu.me.fdsz.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableBaseEntity implements Serializable {

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastModifiedDate;

}
