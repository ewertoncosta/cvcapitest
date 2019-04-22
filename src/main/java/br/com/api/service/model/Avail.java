package br.com.api.service.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Avail {
    private Long cityCode;
    private LocalDate checkin;
    private LocalDate checkout;
    private Integer numAdult;
    private Integer numChild;
}
