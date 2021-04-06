package br.com.goldenraps.goldenrapspayments.handlers;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddPaymentInput {

	private String name;

	private BigDecimal value;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate paymentDate;

}
