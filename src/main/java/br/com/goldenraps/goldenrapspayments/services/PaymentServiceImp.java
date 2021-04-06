package br.com.goldenraps.goldenrapspayments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.goldenraps.goldenrapspayments.handlers.AddPaymentInput;
import br.com.goldenraps.goldenrapspayments.helpers.PaymentCalculator;
import br.com.goldenraps.goldenrapspayments.helpers.PaymentConverter;
import br.com.goldenraps.goldenrapspayments.helpers.PaymentValidationException;
import br.com.goldenraps.goldenrapspayments.helpers.PaymentValidator;
import br.com.goldenraps.goldenrapspayments.models.PaymentDTO;
import br.com.goldenraps.goldenrapspayments.models.PaymentEntity;
import br.com.goldenraps.goldenrapspayments.repositories.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private PaymentConverter converter;

	@Autowired
	private PaymentCalculator calc;

	@Autowired
	private PaymentValidator validator;

	@Override
	public PaymentDTO addPayment(AddPaymentInput input) throws PaymentValidationException {
		validator.validate(input);
		PaymentDTO dto = converter.toDto(input);
		dto = calc.calculateDates(dto);
		PaymentEntity entity = converter.toEntity(dto);

		return converter.toDto(repository.save(entity));
	}

	@Override
	public List<PaymentDTO> listPayments() {
		return repository.findAll().stream().map(entity -> calc.calculateValues(converter.toDto(entity)))
				.collect(Collectors.toList());
	}

}
