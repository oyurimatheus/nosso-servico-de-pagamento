package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import java.math.BigDecimal;

enum FareType {
    CASH {
        @Override
        public BigDecimal calculate(BigDecimal fare, BigDecimal total) {
            return fare;
        }
    },
    PERCENTAGE {
        @Override
        public BigDecimal calculate(BigDecimal fare, BigDecimal total) {
            return fare.multiply(total);
        }
    };

    public abstract BigDecimal calculate(BigDecimal fare, BigDecimal total);
}
