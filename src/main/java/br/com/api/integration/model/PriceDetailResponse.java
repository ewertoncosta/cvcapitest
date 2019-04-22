package br.com.api.integration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDetailResponse implements Serializable {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;
}
