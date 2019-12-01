package hu.me.fdsz.controller;

import hu.me.fdsz.dto.BookingDTO;
import hu.me.fdsz.service.api.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

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

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteBooking(long bookingId) {
        return new ResponseEntity<>(bookingService.deleteBooking(bookingId) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/all-to-current-user")
    public List<BookingDTO> getAllBookingToCurrentUser() throws AuthenticationException {
        return bookingService.getAllBookingToCurrentUser();
    }

    public List<BookingDTO> getAllBooking() {
        return bookingService.getAllBooking();
    }

}
