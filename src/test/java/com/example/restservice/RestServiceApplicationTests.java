package com.example.restservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    String requestBody = "{\n" +
            "  \"addressVO\": [\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"postcode\": \"EC2 3CD\",\n" +
            "      \"type\": \"HOME\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"postcode\": \"EC1 1QR\",\n" +
            "      \"type\": \"OFFICE\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Jack\"\n" +
            "}\n";

    /**
     * @param //person id
     * @apiNote Test to verify the Json response after get API cal with 3rd party call serving as intermediary
     * @implSpec /api/v1/people/1
     * @implNote Test GET
     */
    @Test
    public void testGetAddress() throws Exception {
        mockMvc.perform(get("/api/v1/people/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.address[0].postcode").value("EC23CD"))
                .andExpect(jsonPath("$.address[0].type").value("HOME")).andExpect(jsonPath("$.address[0].id").value(0))
                .andDo(MockMvcResultHandlers.print());

    }

    /**
     * @param //Person Id and request payload for the address
     * @apiNote Test to verify the Json response for the PUT call
     * @implSpec /api/v1/people/1
     * @implNote Test PUT
     */
    @Test
    public void testPutMethod() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/v1/people/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestBody);
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk()).andDo(MockMvcResultHandlers.print());
        ;
    }
}
