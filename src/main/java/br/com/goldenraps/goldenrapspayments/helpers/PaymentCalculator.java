package br.com.goldenraps.goldenrapspayments.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import br.com.goldenraps.goldenrapspayments.models.PaymentDTO;

@Component
public class PaymentCalculator {

	private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

	public PaymentDTO calculateDates(PaymentDTO payment) {
		if (payment.getPaymentDate().isAfter(payment.getDueDate())) {
			long days = ChronoUnit.DAYS.between(payment.getDueDate(), payment.getPaymentDate());

			if (days > 5) {
				payment.setFees(BigDecimal.valueOf(0.3));
				payment.setFine(BigDecimal.valueOf(5));
			} else if (days < 3) {
				payment.setFees(BigDecimal.valueOf(0.1));
				payment.setFine(BigDecimal.valueOf(2));
			} else {
				payment.setFees(BigDecimal.valueOf(0.2));
				payment.setFine(BigDecimal.valueOf(3));
			}

			payment.setDelay(days);
		} else {
			payment.setFees(BigDecimal.ZERO);
			payment.setFine(BigDecimal.ZERO);
			payment.setDelay(0l);
		}

		return payment;
	}

	public PaymentDTO calculateValues(PaymentDTO dto) {
		dto.setCalculateValue(calculate(dto.getValue(), dto.getFine(), dto.getFees(), dto.getDelay()));
		return dto;
	}

	private BigDecimal calculate(BigDecimal value, BigDecimal fine, BigDecimal fees, long days) {
		BigDecimal calculatedFee = fees.multiply(BigDecimal.valueOf(days)).divide(HUNDRED, 12, RoundingMode.HALF_UP)
				.add(BigDecimal.ONE);
		BigDecimal calculatedFine = fine.divide(HUNDRED, 12, RoundingMode.HALF_UP).add(BigDecimal.ONE);

		return value.multiply(calculatedFine).multiply(calculatedFee).setScale(2, RoundingMode.HALF_UP);
	}

}
