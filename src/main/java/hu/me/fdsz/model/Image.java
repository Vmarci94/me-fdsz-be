package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Image implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] data;

}
