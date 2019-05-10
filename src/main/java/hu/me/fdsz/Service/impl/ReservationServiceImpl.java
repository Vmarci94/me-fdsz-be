package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.ReservationService;
import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.model.key.TurnusKey;
import hu.me.fdsz.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservationToTurnus(TurnusKey turnusKey) {
        return reservationRepository.findAllByTurnusKey(turnusKey).orElseThrow(EntityNotFoundException::new);
    }


}
