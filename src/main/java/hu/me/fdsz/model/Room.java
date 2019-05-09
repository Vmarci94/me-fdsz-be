package hu.me.fdsz.model;

import hu.me.fdsz.model.enums.RoomType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    private Long id;

    @Column(name = "number_of_beds", nullable = false)
    private int numberOfBeds;

    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price")
    private Long price;

//    @Column(name = "room_ovner")
//    private User roomOwner;


}
