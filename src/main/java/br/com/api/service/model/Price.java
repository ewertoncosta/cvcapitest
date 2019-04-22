package br.com.api.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price implements Serializable {

    private BigDecimal adult;
    private BigDecimal child;

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;
}
