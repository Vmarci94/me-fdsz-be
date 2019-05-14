package hu.me.fdsz.dao.impl;

import hu.me.fdsz.dao.api.TurnusDao;
import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TurnusDaoImpl implements TurnusDao {

    private final EntityManager entityManager;

    @Autowired
    public TurnusDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reservation> getAllReservation(Turnus turnus) {
        String hql = "select r from Turnus t, Reservation r " +
                " where t.startDate = r.startDate and t.endDate = r.endDate " +
                " and t = :currentTurnus";
        return entityManager.createQuery(hql, Reservation.class)
                .setParameter("currentTurnus", turnus)
                .getResultList();
    }

    @Override
    public List<Room> getBookedRooms(Turnus turnus) {
        return getAllReservation(turnus).stream().map(Reservation::getRoom).collect(Collectors.toList());
    }

}
