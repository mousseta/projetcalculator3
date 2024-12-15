package com.gretadevops.calculator;

import com.gretadevops.calculator.Calculator; // Import nécessaire
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorIntegrationTest {

    @LocalServerPort
    private int port;

    private final Calculator calculator = new Calculator(); // Instanciation de la classe Calculator

    @Test
    void testCalculatorService() {
        int result = calculator.sum(3, 7);
        assertEquals(10, result, "3 + 7 should equal 10");
    }

    @Test
    void testCalculatorController() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/sum?a=4&b=8";
        String response = restTemplate.getForObject(url, String.class);

        assertEquals("12", response, "Response should be '12' for a=4 and b=8");
    }
}
