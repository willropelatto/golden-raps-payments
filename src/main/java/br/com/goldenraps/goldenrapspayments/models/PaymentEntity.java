package br.com.goldenraps.goldenrapspayments.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "payments")
public class PaymentEntity {

	@Id
	private UUID id;

	private String name;

	private BigDecimal value;

	private LocalDate dueDate;

	private LocalDate paymentDate;

	private BigDecimal fees;

	private BigDecimal fine;

	private long delay;
}
