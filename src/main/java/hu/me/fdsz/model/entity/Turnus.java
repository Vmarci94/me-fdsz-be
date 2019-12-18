package hu.me.fdsz.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Turnus extends BaseEntity {

    @Column(name = "start_date", unique = true, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "end_date", unique = true, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Type(type = "yes_no")
    @Column(nullable = false)
    private boolean enabled;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id", nullable = false)
    private User lastModifiedBy;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "turnus_room",
            joinColumns = {@JoinColumn(name = "turnusId")},
            inverseJoinColumns = {@JoinColumn(name = "roomId", unique = true)}
    )
    @MapKey(name = "roomNumber")
    private Map<Long, Room> rooms = new HashMap<>();

}
