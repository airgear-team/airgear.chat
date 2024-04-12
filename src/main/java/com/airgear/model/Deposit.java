package com.airgear.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Deposit {
    private BigDecimal amount;
    private Currency currency;

    enum Currency {UAH, EUR, USD}
}