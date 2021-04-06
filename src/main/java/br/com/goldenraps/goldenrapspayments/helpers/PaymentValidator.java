package br.com.goldenraps.goldenrapspayments.helpers;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.stereotype.Component;

import br.com.goldenraps.goldenrapspayments.handlers.AddPaymentInput;

@Component
public class PaymentValidator {

	public void validate(AddPaymentInput payment) throws PaymentValidationException {
		if (isBlank(payment.getName())) {
			throw new PaymentValidationException("O nome do pagamento deve ser informado");
		}

		if (isNull(payment.getValue())) {
			throw new PaymentValidationException("O valor do pagamento deve ser informado");
		}

		if (isNull(payment.getDueDate())) {
			throw new PaymentValidationException("A data de vencimento do pagamento deve ser informada");
		}

		if (isNull(payment.getPaymentDate())) {
			throw new PaymentValidationException("A data de pagamento deve ser informada");
		}

	}

}
