package hu.me.fdsz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "reciever", referencedColumnName = "id", nullable = false)
    private User reciever;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

}
