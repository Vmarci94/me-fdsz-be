package hu.me.fdsz.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Messages implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "sender", referencedColumnName = "id", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "recipient", referencedColumnName = "id", nullable = false)
    private User recipient;

    @Column(nullable = false)
    private boolean secret;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Messages replyTo;


}
