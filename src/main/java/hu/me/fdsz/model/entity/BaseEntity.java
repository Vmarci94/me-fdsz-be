package hu.me.fdsz.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class BaseEntity extends AuditableBaseEntity {

    /**
     * Egy entitás egyedi azonosítója, természetesen null értékkel nem lehet perzisztálni, a keretrendszer
     * {@link NullPointerException}-t fog dobni. Mégis azért nem használok <b>long</b> primitívet mert a {@link ModelMapper}
     */
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;

}
