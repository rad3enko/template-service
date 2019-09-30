package com.rad3enko.templateservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.rad3enko.templateservice.api.dto.CreateTemplateInDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created on 30.09.2019.
 *
 * @author Sergey Radchenko
 */
@AutoConfigureMockMvc
@EnablePostgresIntegrationTest
class TemplateControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @ExpectedDataSet(value = "dataset/api/create-template.json")
    void testCreate() throws Exception {
        // Arrange
        final String name = "new_name";
        final String value = "new_value";

        CreateTemplateInDto createDto = CreateTemplateInDto.builder()
                                                           .name(name)
                                                           .value(value)
                                                           .build();

        // Act
        mockMvc.perform(post("/templates/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDto)))
               // Assert
               .andExpect(status().isOk());
    }
}
