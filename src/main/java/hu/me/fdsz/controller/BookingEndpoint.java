package hu.me.fdsz.controller;

import hu.me.fdsz.dto.BookingDTO;
import hu.me.fdsz.dto.GuestDTO;
import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.service.api.BookingService;
import hu.me.fdsz.service.api.RoomService;
import hu.me.fdsz.service.api.TurnusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/booking")
public class BookingEndpoint {

    private final TurnusService turnusService;

    private final RoomService roomService;
    private final ModelMapper modelMapper;
    private final BookingService bookingService;


    @Autowired
    public BookingEndpoint(TurnusService turnusService, RoomService roomService, ModelMapper modelMapper, BookingService bookingService) {
        this.turnusService = turnusService;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/all-aviable-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllAviableTurnus() {
        return turnusService.getAllAviableTurnus();
    }

    @GetMapping(value = "/all-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnus() {
        return turnusService.getAllTurnus().stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/all-actual-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnusInYear() {
        return turnusService.getAllActualTurnus();
    }

    @PostMapping(value = "/get-avaiable-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDTO> getAvaiableRooms(@RequestBody TurnusDTO turnusDTO) {
        return roomService.getAvaiableRooms(turnusDTO);
    }

    @PostMapping(value = "/add-new-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public TurnusDTO addNewTurnus(@RequestBody TurnusDTO turnusDTO) throws AuthenticationException {
        return modelMapper.map(turnusService.addNewTurnus(turnusDTO), TurnusDTO.class);
    }

    @PostMapping(value = "/add-new-booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDTO booking(@RequestParam long turnusId, @RequestParam long roomNumber, @RequestBody(required = false) List<GuestDTO> guests) {
        return modelMapper.map(bookingService.add(turnusId, roomNumber, guests), BookingDTO.class);
    }

}
