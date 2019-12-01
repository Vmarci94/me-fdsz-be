package hu.me.fdsz.controller;

import hu.me.fdsz.dto.TurnusDTO;
import hu.me.fdsz.service.api.TurnusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/turnus")
public class TurnusEndpoint {

    private final TurnusService turnusService;

    private final ModelMapper modelMapper;

    @Autowired
    public TurnusEndpoint(TurnusService turnusService, ModelMapper modelMapper) {
        this.turnusService = turnusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/all-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TurnusDTO> getAllTurnus() {
        return turnusService.getAllTurnus().stream().map(turnus -> modelMapper.map(turnus, TurnusDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteTurnus(long turnusId) {
        return turnusService.delete(turnusId);
    }

    @PostMapping(value = "/add-new-turnus", produces = MediaType.APPLICATION_JSON_VALUE)
    public TurnusDTO addNewTurnus(@RequestBody TurnusDTO turnusDTO) throws AuthenticationException {
        return modelMapper.map(turnusService.addNewTurnus(turnusDTO), TurnusDTO.class);
    }


}
