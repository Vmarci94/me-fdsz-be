package hu.me.fdsz.model.key;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class TurnusKey implements Serializable {

    private Long number;

    private Long year;

}
