package br.com.api.integration.converter;

import br.com.api.integration.model.HotelResponse;
import br.com.api.integration.model.PriceDetailResponse;
import br.com.api.integration.model.RoomResponse;
import br.com.api.service.model.Hotel;
import br.com.api.service.model.Price;
import br.com.api.service.model.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AvailConverter {

    public List<HotelResponse> converterListHotel(List<Hotel> hotelsList){

        List<HotelResponse> hotelResponseList = new ArrayList<HotelResponse>();

        for ( Hotel hotel: hotelsList ) {

            HotelResponse convertedHotel = convertHotel(hotel);
            hotelResponseList.add(convertedHotel);

            convertedHotel.setRooms(converterListRoom(hotel.getRooms()));

        }

        return hotelResponseList;
    }



    public List<RoomResponse> converterListRoom(List<Room> roomsList){

        List<RoomResponse> roomResponseList = new ArrayList<RoomResponse>();

        for (Room room: roomsList) {
            RoomResponse convertedRoom = convertRoom(room);
            convertedRoom.setPriceDetail(convertPrice(room.getPrice()));
            roomResponseList.add(convertedRoom);
        }

        return roomResponseList;
    }

    public HotelResponse convertHotel(Hotel hotel) {

        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setCityName(hotel.getCityName());
        hotelResponse.setId(hotel.getId());
        hotelResponse.setRooms(hotelResponse.getRooms());

        return hotelResponse;
    }

    public RoomResponse convertRoom(Room room) {

        RoomResponse roomResponseList = new RoomResponse();
        roomResponseList.setRoomID(room.getRoomID());
        roomResponseList.setCategoryName(room.getCategoryName());
        roomResponseList.setTotalPrice(room.getTotalPrice());

        return roomResponseList;
    }

    public PriceDetailResponse convertPrice(Price price) {

        PriceDetailResponse priceDetailResponse = new PriceDetailResponse();
        priceDetailResponse.setPricePerDayAdult(price.getPricePerDayAdult());
        priceDetailResponse.setPricePerDayChild(price.getPricePerDayChild());

        return priceDetailResponse;
    }
}
