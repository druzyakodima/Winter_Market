package com.winter.market.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private BigDecimal priceMinFilter;
    private BigDecimal priceMaxFilter;
    private String titleFilter;
}
