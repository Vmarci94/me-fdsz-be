package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.ReservationDTO;
import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.repository.ReservationRepository;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.service.api.ReservationService;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, RoomRepository roomRepository, ModelMapper modelMapper, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public boolean addNewReservation(ReservationDTO reservationDTO) throws AuthenticationException {
        Reservation newReservation = modelMapper.map(reservationDTO, Reservation.class);
        newReservation.setRoom(roomRepository.findById(reservationDTO.getRoomNumber())
                .orElseThrow(EntityNotFoundException::new));
        newReservation.setRoomOwner(userService.getCurrentUser().orElseThrow(AuthenticationException::new));
        return reservationRepository.save(newReservation) != null;
    }
}
