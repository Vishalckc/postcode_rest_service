package com.example.restservice;

import com.example.restservice.service.RestService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceWireMockTest {
    private int port = 3901;
    private String apiHostedServer="http://localhost:8081";
    private String dummyServer = "http://localhost:" + port;

    private WireMockServer mockServer;
    private RestService service;


    @Before
    public void init() {
        //  ReflectionTestUtils.setField(controller, "baseUrl", url);
        wireMockRule.resetMappings();
        wireMockRule.resetScenarios();
        wireMockRule.resetRequests();
    }


    private String body = "{\n" +
            "      \"postcode\": \"EC2 3CD\",\n" +
            "      \"type\": \"HOME\"\n" +
            "    }";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(port);

    @Test
    public void contextLoads() {

    }


    /**
     * @apiNote Wire Mock testing of 3rd party Api integration with Rest Service
     * @implNote Use of Actual server and dummy server for displaying API connectivity before and after interruption occurs of 3rd party service
     * @implSpec First test with apiHostedServer  and then replace with dummyServer variable
     * */
    @Test
    @Description("Get address details from an external service")
    public void getAddressDetails() {
        //Stub the endpoint
        stubFor(get(urlPathMatching("/api/v1/address/postcodes/EC23CD"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody(body)
                )
        );
        //Make a request
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = apiHostedServer;
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "/api/v1/address/postcodes/EC23CD", String.class);
        //Verify
        assertNotNull(response);
        assertTrue("Status code not equals to 200", response.getStatusCode().equals(HttpStatus.OK));
        assertTrue("Contains fail", response.getBody().contains("HOME"));
    }

}
