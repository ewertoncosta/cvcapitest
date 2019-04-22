package br.com.api.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel implements Serializable {

    private Long id;
    private String name;
    private String cityCode;
    private String cityName;
    private List<Room> rooms;

}
