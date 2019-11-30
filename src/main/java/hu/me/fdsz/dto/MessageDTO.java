package hu.me.fdsz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDTO {

    private String message;

    private Date createdDate;

    private UserDTO sender;

    private UserDTO reciever;

    private String readed;

    private boolean myMessage;

}
