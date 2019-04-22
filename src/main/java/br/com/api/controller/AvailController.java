package br.com.api.controller;

import br.com.api.integration.converter.AvailConverter;
import br.com.api.integration.model.HotelResponse;
import br.com.api.service.model.Hotel;
import br.com.api.service.AvailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/avail")
public class AvailController {

    @Autowired
    private AvailService availService;

    @Autowired
    private AvailConverter availConverter;

    @GetMapping("/{id}")
    public ResponseEntity<List<HotelResponse>> getHotelsByCity(@RequestParam(name = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate checkin
                                                               ,@RequestParam(name = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate checkout
                                                               ,@RequestParam(name = "numAdult") BigDecimal numAdult
                                                               ,@RequestParam(name = "numChild") BigDecimal numChild
                                                               ,@PathVariable(value = "id") Long id){

        List<HotelResponse> hotelResponses = availConverter.converterListHotel(availService.getHotelsByCity(checkin,checkout,numAdult, numChild,id));

        return ResponseEntity.ok().body(hotelResponses);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<HotelResponse>> getHotelbyId(@RequestParam(name = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate checkin
                                              ,@RequestParam(name = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate checkout
                                              ,@RequestParam(name = "numAdult") BigDecimal numAdult
                                              ,@RequestParam(name = "numChild") BigDecimal numChild
                                              ,@PathVariable(value = "id") Long id){

        List<HotelResponse> hotelResponse = availConverter.converterListHotel(availService.getHotelbyId(checkin, checkout, numAdult, numChild, id));

        return ResponseEntity.ok().body(hotelResponse);
    }

}
