package com.lucas.challenge.builders.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lucas.challenge.builders.feature.client.domain.Client;
import com.lucas.challenge.builders.feature.client.dto.CreateClientDTO;
import com.lucas.challenge.builders.feature.client.dto.UpdateClientDTO;
import com.lucas.challenge.builders.feature.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Sql(scripts = {"/delete_all_client.sql", "/insert_client.sql"})
    void givenName_whenGetClient_thenReturnSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/v1/client/search")
                        .param("name", "luke")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].name", containsString("luke")));
    }


    @Test
    @Sql(scripts = {"/delete_all_client.sql", "/insert_client.sql"})
    void getAllClient_thenReturnSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/v1/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].name").exists());
    }

    @Test
    void givenClientData_whenSaveClient_thenReturnSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        CreateClientDTO client = new CreateClientDTO();
        client.setName("Juliana");
        client.setEmail("ju@teste.com");
        client.setCellphone("61988889999");
        client.setBirthDate(LocalDate.of(1995, 04, 27));

        mvc.perform(MockMvcRequestBuilders
                        .post("/v1/client/")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").exists());

        var clientDB = clientRepository.findById(Long.valueOf(1)).orElse(new Client());
        assertEquals("ju@teste.com", clientDB.getEmail());
    }

    @Test
    @Sql(scripts = {"/delete_all_client.sql", "/insert_client.sql"})
    void givenClientData_whenUpdateClient_thenReturnSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        UpdateClientDTO client = new UpdateClientDTO();
        client.setId(Long.valueOf(1));
        client.setName("punchman");
        client.setEmail("punchman@teste.com");
        client.setCellphone("6199998888");
        client.setBirthDate(LocalDate.of(1995, 04, 27));

        mvc.perform(MockMvcRequestBuilders
                        .patch("/v1/client")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").exists());

        var clientDB = clientRepository.findById(Long.valueOf(1)).orElse(new Client());
        assertEquals("punchman@teste.com", clientDB.getEmail());
    }


}
