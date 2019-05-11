package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.RoomService;
import hu.me.fdsz.Service.api.TurnusService;
import hu.me.fdsz.dto.RoomDTO;
import hu.me.fdsz.dto.TurnusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/resort")
public class ResortEndpoint {

    private final TurnusService turnusService;

    private final RoomService roomService;

    @Autowired
    public ResortEndpoint(TurnusService turnusService, RoomService roomService) {
        this.turnusService = turnusService;
        this.roomService = roomService;
    }

    @GetMapping(value = "/get-all-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnus() {
        return turnusService.getAllAviableTurnus();
    }

    @PostMapping(value = "/add-new-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewTurnus(@RequestBody TurnusDTO turnusDTO) {
        turnusService.addNewTurnus(turnusDTO);
    }

    @PostMapping(value = "/add-new-room", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewRoom(@RequestBody RoomDTO roomDTO) {
        roomService.addNewRoom(roomDTO);
    }


}
