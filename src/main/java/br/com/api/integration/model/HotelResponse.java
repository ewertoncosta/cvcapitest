package br.com.api.integration.model;

import br.com.api.service.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelResponse implements Serializable {

    private Long id;
    private String cityName;
    private List<RoomResponse> rooms;

}
