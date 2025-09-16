package cl.prueba.proyectum;

import cl.prueba.proyectum.models.ProductoResponse;
import cl.prueba.proyectum.services.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProyectumApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@TestConfiguration
	static class TestConfig {
		@Bean
		@Primary
		ProductoService productoService() {
			return Mockito.mock(ProductoService.class);
		}
	}

	@Autowired
	private ProductoService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser(username="admin", roles={"USER"})
	void listar_deberiaRetornarListaDeProductos() throws Exception {
		ProductoResponse producto = new ProductoResponse(1L, "Laptop", 1200.0);
		Mockito.when(service.listar()).thenReturn(List.of(producto));

		mockMvc.perform(get("/api/productos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nombre").value("Laptop"));
	}
}
