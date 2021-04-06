package br.com.goldenraps.goldenrapspayments.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraps.goldenrapspayments.helpers.PaymentValidationException;
import br.com.goldenraps.goldenrapspayments.models.PaymentDTO;
import br.com.goldenraps.goldenrapspayments.services.PaymentService;

@RestController
public class PaymentHandler {

	@Autowired
	private PaymentService service;

	@PostMapping("/add")
	public ResponseEntity<Object> addPayment(@RequestBody AddPaymentInput payment) {
		try {
			PaymentDTO dto = service.addPayment(payment);
			return ResponseEntity.ok(dto);
		} catch (PaymentValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@GetMapping("/list")
	public ResponseEntity<Object> listPayments() {
		try {
			List<PaymentDTO> listPayments = service.listPayments();
			return ResponseEntity.ok(listPayments);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
