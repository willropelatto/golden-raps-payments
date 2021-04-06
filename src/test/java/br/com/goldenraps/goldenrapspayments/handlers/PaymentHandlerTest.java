package br.com.goldenraps.goldenrapspayments.handlers;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PaymentHandlerTest {

	private static final BigDecimal VALOR_PADRAO = valueOf(1053.00);
	@Autowired
	private MockMvc mvc;

	@Test
	void testInclusaoSucessoSemMulta1() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("EMDIA");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));
		input.setPaymentDate(LocalDate.of(2020, 4, 4));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.name").value(input.getName()))
				.andExpect(jsonPath("$.value").value(VALOR_PADRAO));
	}

	@Test
	void testInclusaoSucessoSemMulta2() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("ANTECIPA");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));
		input.setPaymentDate(LocalDate.of(2020, 4, 1));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.name").value(input.getName()))
				.andExpect(jsonPath("$.value").value(VALOR_PADRAO));
	}

	@Test
	void testInclusaoSucessoComMulta1() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("MULTA1D");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));
		input.setPaymentDate(LocalDate.of(2020, 4, 5));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.name").value(input.getName()))
				.andExpect(jsonPath("$.value").value(VALOR_PADRAO));
	}

	@Test
	void testInclusaoSucessoComMulta2() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("MULTA3D");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));
		input.setPaymentDate(LocalDate.of(2020, 4, 7));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.name").value(input.getName()))
				.andExpect(jsonPath("$.value").value(VALOR_PADRAO));
	}

	@Test
	void testInclusaoSucessoComMulta3() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("MULTA10D");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));
		input.setPaymentDate(LocalDate.of(2020, 4, 14));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.name").value(input.getName()))
				.andExpect(jsonPath("$.value").value(VALOR_PADRAO));
	}

	@Test
	void testListSucesso() throws Exception {
		mvc.perform(get("/list").contentType(MediaType.APPLICATION_JSON)) //
				// .andDo(print()) //
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5))) //
				.andExpect(jsonPath("$.[*].name",
						containsInAnyOrder("EMDIA", "ANTECIPA", "MULTA1D", "MULTA3D", "MULTA10D")))
				.andExpect(
						jsonPath("$.[*].calculateValue", containsInAnyOrder(1053.0, 1053.0, 1075.13, 1091.1, 1138.82)))
				.andExpect(jsonPath("$.[*].fees", containsInAnyOrder(0, 0.1, 0.2, 0, 0.3)))
				.andExpect(jsonPath("$.[*].fine", containsInAnyOrder(2, 5, 3, 0, 0)));
	}

	@Test
	void testErroDataVencimento() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("DUP0123");
		input.setValue(VALOR_PADRAO);
		input.setPaymentDate(LocalDate.of(2020, 4, 4));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()) //
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().string("A data de vencimento do pagamento deve ser informada"));
	}

	@Test
	void testErroDataPagamento() throws Exception {
		AddPaymentInput input = new AddPaymentInput();
		input.setName("DUP0123");
		input.setValue(VALOR_PADRAO);
		input.setDueDate(LocalDate.of(2020, 4, 4));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = objectMapper.writeValueAsString(input);

		mvc.perform(MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()) //
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(content().string("A data de pagamento deve ser informada"));
	}

}
