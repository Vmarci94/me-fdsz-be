package hu.me.fdsz.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDTO {

    private String message;

    @Temporal(DATE)
    private Date createdDate;

    private UserDTO sender;

    private UserDTO reciever;

    private String readed;

    private boolean myMessage;

}
