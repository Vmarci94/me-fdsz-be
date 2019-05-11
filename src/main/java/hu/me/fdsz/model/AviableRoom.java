package hu.me.fdsz.model;

import hu.me.fdsz.model.Keys.AviableRoomKey;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AviableRoom {

    @EmbeddedId
    private AviableRoomKey id;

}
