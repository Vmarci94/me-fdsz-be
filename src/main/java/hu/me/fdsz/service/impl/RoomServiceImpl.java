package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.Room;
import hu.me.fdsz.model.Turnus;
import hu.me.fdsz.model.dto.RoomDTO;
import hu.me.fdsz.model.enums.RoomType;
import hu.me.fdsz.repository.RoomRepository;
import hu.me.fdsz.service.api.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room addNewRoom(RoomDTO roomDTO) {
        Room newRoom = modelMapper.map(roomDTO, Room.class);
        return roomRepository.save(newRoom);
    }

    @Override
    public List<Room> getAvaiableRooms(Turnus turnus) {
        Collection<Room> allRoomToTurnus = turnus.getRooms().values();
        return null;

    }

    @Override
    public Room createRoom(long roomNumber, RoomType roomType) {
        return Room.builder()
                .roomNumber(roomNumber)
                .roomType(roomType)
                .price(getPriceToRoomType(roomType))
                .build();
    }

    @Override
    public long getPriceToRoomType(RoomType roomType) {
        Long result = null;
        switch (roomType) {
            case THREE_BED:
                result = 5000L;
                break;
            case FOUR_BED:
                result = 10000L;
                break;
            default:
                throw new IllegalArgumentException("nincs felkészítve a rendszer a(z) " + roomType.toString() + " árára.");
        }
        return result;
    }


}
