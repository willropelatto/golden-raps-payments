package br.com.goldenraps.goldenrapspayments.helpers;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.goldenraps.goldenrapspayments.handlers.AddPaymentInput;
import br.com.goldenraps.goldenrapspayments.models.PaymentDTO;
import br.com.goldenraps.goldenrapspayments.models.PaymentEntity;

@Component
public class PaymentConverter {

	public PaymentEntity toEntity(PaymentDTO payment) {
		PaymentEntity entity = new PaymentEntity();
		entity.setId(UUID.randomUUID());
		entity.setName(payment.getName());
		entity.setValue(payment.getValue());
		entity.setDueDate(payment.getDueDate());
		entity.setPaymentDate(payment.getPaymentDate());
		entity.setFees(payment.getFees());
		entity.setFine(payment.getFine());
		entity.setDelay(payment.getDelay());

		return entity;
	}

	public PaymentDTO toDto(PaymentEntity entity) {
		PaymentDTO dto = new PaymentDTO();
		dto.setName(entity.getName());
		dto.setValue(entity.getValue());
		dto.setDueDate(entity.getDueDate());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setFees(entity.getFees());
		dto.setFine(entity.getFine());
		dto.setDelay(entity.getDelay());
		dto.setCalculateValue(BigDecimal.ZERO);

		return dto;
	}

	public PaymentDTO toDto(AddPaymentInput payment) {
		PaymentDTO dto = new PaymentDTO();
		dto.setName(payment.getName());
		dto.setValue(payment.getValue());
		dto.setDueDate(payment.getDueDate());
		dto.setPaymentDate(payment.getPaymentDate());
		return dto;
	}

}
