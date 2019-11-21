package hu.me.fdsz.controller;

import hu.me.fdsz.dto.ReservationDTO;
import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.service.api.ReservationService;
import hu.me.fdsz.service.api.RoomService;
import hu.me.fdsz.service.api.TurnusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/resort")
public class ResortEndpoint {

    private final TurnusService turnusService;

    private final RoomService roomService;

    private final ReservationService reservationService;

    @Autowired
    public ResortEndpoint(TurnusService turnusService, RoomService roomService, ReservationService reservationService) {
        this.turnusService = turnusService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/get-all-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnus() {
        return turnusService.getAllAviableTurnus();
    }

    @GetMapping(value = "/get-all-turnus-in-year", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnusInYear(Integer year) {
        return turnusService.getAllTurnusInYear(year);
    }

    @GetMapping(value = "/get-turnus-years", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getTurnusYears() {
        return turnusService.getTurnusYears();
    }

    @PostMapping(value = "/get-avaiable-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDTO> getAvaiableRooms(@RequestBody TurnusDTO turnusDTO) {
        return roomService.getAvaiableRooms(turnusDTO);
    }

    @PostMapping(value = "/add-new-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewTurnus(@RequestBody TurnusDTO turnusDTO) {
        turnusService.addNewTurnus(turnusDTO);
    }

    @PostMapping(value = "/add-new-room", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewRoom(@RequestBody RoomDTO roomDTO) {
        roomService.addNewRoom(roomDTO);
    }

    @PostMapping(value = "/add-new-reservation")
    public ResponseEntity<HttpStatus> addNewRoom(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.addNewReservation(reservationDTO) ?
                new ResponseEntity<>(HttpStatus.OK)
                :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
