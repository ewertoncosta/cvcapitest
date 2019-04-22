package br.com.api.service;

import br.com.api.integration.model.HotelResponse;
import br.com.api.service.model.Hotel;
import br.com.api.service.model.Price;
import br.com.api.service.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class AvailService {

    @Autowired
    RestTemplate restTemplate;

    String urlAvail =  "https://cvcbackendhotel.herokuapp.com/hotels/avail/";
    String urlDetail = "https://cvcbackendhotel.herokuapp.com/hotels/";

    private final BigDecimal markup = BigDecimal.valueOf(0.70);

    public List<Hotel> getHotelbyId(LocalDate checkin
                             ,LocalDate checkout
                             ,BigDecimal numAdult
                             ,BigDecimal numChild
                             ,Long id) {

        ResponseEntity<List<Hotel>> response = restTemplate.exchange(urlDetail + "{id}"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<Hotel>>() {}
                , id);

        for (Hotel hotel: response.getBody()) {
            calculate(hotel.getRooms(), checkin, checkout, numAdult, numChild);
        }

        return response.getBody();
    }

    public List<Hotel> getHotelsByCity(LocalDate checkin
                                       ,LocalDate checkout
                                       ,BigDecimal numAdult
                                       ,BigDecimal numChild
                                       ,Long id){

        ResponseEntity<List<Hotel>> response = restTemplate.exchange(urlAvail + "{id}"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<Hotel>>() {}
                , id);

        List<Hotel> hotelList = response.getBody();

        for(Hotel hotel: hotelList) {
            calculate(hotel.getRooms(),checkin, checkout, numAdult, numChild);
        }

        return hotelList;
    }

    public void calculate(List<Room> rooms, LocalDate checkin, LocalDate checkout, BigDecimal numAdult, BigDecimal numChild) {

        for(Room room: rooms) {

            room.setTotalPrice(getTotalPrice(room.getPrice(), checkin, checkout, numAdult, numAdult));
            room.getPrice().setPricePerDayAdult(getTotalPricePerAdult(room.getPrice(), checkin, checkout, numAdult));
            room.getPrice().setPricePerDayChild(getTotalPricePerChild(room.getPrice(), checkin, checkout, numChild));

        }
    }

    public BigDecimal getTotalPrice(Price price, LocalDate checkin, LocalDate checkout, BigDecimal numAdult, BigDecimal numChild) {
        return getTotalPricePerChild(price, checkin, checkout, numChild).add(getTotalPricePerAdult(price, checkin, checkout, numChild));
    }
    public BigDecimal getTotalPricePerAdult(Price price, LocalDate checkin, LocalDate checkout, BigDecimal numAdult) {
        final BigDecimal days = BigDecimal.valueOf(getStayDays(checkin, checkout));
        return price.getAdult().multiply(days).divide(markup, RoundingMode.HALF_UP).multiply(numAdult);
    }

    public BigDecimal getTotalPricePerChild(Price price, LocalDate checkin, LocalDate checkout, BigDecimal numChild) {
        final BigDecimal days = BigDecimal.valueOf(getStayDays(checkin, checkout));
        return price.getChild().multiply(days).divide(markup, RoundingMode.HALF_UP).multiply(numChild);
    }

    public Long getStayDays(LocalDate checkin, LocalDate checkout){
        return Duration.between(checkin.atStartOfDay(),checkout.atStartOfDay()).toDays();
    }

}