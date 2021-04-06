package br.com.goldenraps.goldenrapspayments.services;

import java.util.List;

import br.com.goldenraps.goldenrapspayments.handlers.AddPaymentInput;
import br.com.goldenraps.goldenrapspayments.helpers.PaymentValidationException;
import br.com.goldenraps.goldenrapspayments.models.PaymentDTO;

public interface PaymentService {

	PaymentDTO addPayment(AddPaymentInput payment) throws PaymentValidationException;

	List<PaymentDTO> listPayments();

}
