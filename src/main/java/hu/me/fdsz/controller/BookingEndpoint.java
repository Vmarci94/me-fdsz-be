package hu.me.fdsz.controller;

import hu.me.fdsz.dto.BookingDTO;
import hu.me.fdsz.service.api.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingEndpoint {


    private final ModelMapper modelMapper;

    private final BookingService bookingService;

    @Autowired
    public BookingEndpoint(ModelMapper modelMapper, BookingService bookingService) {
        this.modelMapper = modelMapper;
        this.bookingService = bookingService;
    }


    @PostMapping(value = "/add-new-booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDTO booking(@RequestBody BookingDTO bookingDTO) {
        return modelMapper.map(bookingService.add(bookingDTO), BookingDTO.class);
    }

}
