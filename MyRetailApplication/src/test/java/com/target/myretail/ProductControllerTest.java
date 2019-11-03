package com.target.myretail;

import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.domain.CurrentPrice;
import com.target.myretail.domain.Product;
import com.target.myretail.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	@LocalServerPort
	int randomServerPort;

	@MockBean
	private ProductRepository mockRepository;

	@Before
	public void init() {

		Product product = new Product();
		product.setId("13860428");
		product.setName("The Big Lebowski (Blu-ray)");
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrency_code("USD");
		currentPrice.setValue(13.49);
		product.setCurrentPrice(currentPrice);
		when(mockRepository.save(product)).thenReturn(product);

	}

	@Test
	public void testGetProduct() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/products/13860428";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("The Big Lebowski"));
	}

	@Test
	public void testPutProduct() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/products/13860428";
		URI uri = new URI(baseUrl);
		
		Product product = new Product();
		product.setId("13860428");
		product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrency_code("USD");
		currentPrice.setValue(13.49);
		product.setCurrentPrice(currentPrice);
		restTemplate.put(uri, product);
	}

}
