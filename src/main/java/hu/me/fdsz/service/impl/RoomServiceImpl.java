package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl {

    @Autowired
    public RoomServiceImpl() {
    }

    //    @Override
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
