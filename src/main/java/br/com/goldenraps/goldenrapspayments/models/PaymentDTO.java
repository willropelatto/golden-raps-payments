package br.com.goldenraps.goldenrapspayments.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDTO {

	private String name;

	private BigDecimal value;

	private BigDecimal calculateValue;

	private LocalDate dueDate;

	private LocalDate paymentDate;

	private BigDecimal fees;

	private BigDecimal fine;

	private long delay;

}
