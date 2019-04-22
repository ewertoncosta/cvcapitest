package br.com.api.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room implements Serializable {

    private Long roomID;
    private String categoryName;
    private Price price;
    private BigDecimal totalPrice;

/*    public final BigDecimal markup = BigDecimal.valueOf(0.70);

    public BigDecimal getTotalPrice() {
        return getTotalPricePerAdult().add(getTotalPricePerChild());
    }

    public BigDecimal getTotalPricePerAdult() {
        final BigDecimal days = BigDecimal.valueOf(getStayDays());
        return price.getAdult().multiply(days).divide(markup, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalPricePerChild() {
        final BigDecimal days = BigDecimal.valueOf(getStayDays());
        return price.getChild().multiply(days).divide(markup, RoundingMode.HALF_UP);
    }

    public Long getStayDays() {
        return Duration.between(checkin.atStartOfDay(),checkout.atStartOfDay()).toDays();
    }

 */

}
