package com.domain.expansion.inventoryservice;

import com.domain.expansion.inventoryservice.dto.InventoryAllResponse;
import com.domain.expansion.inventoryservice.dto.WebResponse;
import com.domain.expansion.inventoryservice.model.Inventory;
import com.domain.expansion.inventoryservice.repository.InventoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryServiceTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        inventoryRepository.deleteAll();
    }

    @Test
    public void getInventories() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setQuantity(100);
        inventory.setSkuCode("poco_f3");
        inventoryRepository.save(inventory);


        mockMvc.perform(
                get("/api/inventory/all").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    WebResponse<InventoryAllResponse> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                    });
                    assertNotNull(webResponse.getData());
                }
        );
    }
}
