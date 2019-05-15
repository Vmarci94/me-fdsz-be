package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Service.api.ReservationService;
import hu.me.fdsz.dto.ReservationDTO;
import hu.me.fdsz.model.Reservation;
import hu.me.fdsz.repository.ReservationRepository;
import hu.me.fdsz.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, RoomRepository roomRepository, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewReservation(ReservationDTO reservationDTO) {
        Reservation newReservation = modelMapper.map(reservationDTO, Reservation.class);
        newReservation.setRoom(roomRepository.findById(reservationDTO.getRoomNumber())
                .orElseThrow(EntityNotFoundException::new));
        newReservation.setRoomOwner(jwtTokenProvider.getAuthenticatedUser());
        return reservationRepository.save(newReservation) != null;
    }
}
