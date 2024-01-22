package com.domain.expansion.productservice;

import com.domain.expansion.productservice.dto.WebResponse;
import com.domain.expansion.productservice.model.Product;
import com.domain.expansion.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        productRepository.deleteAll();
    }

    @Test
    public void deleteTest() throws Exception {
        Product product = new Product();
        product.setId("aqwaqwaqw");
        product.setName("HAHAHAHAHAHHAHAHAHA");
        product.setDescription("HAHAHAHAHAHAHAHAHAHAHAH");
        product.setPrice(BigDecimal.valueOf(1000));
        productRepository.save(product);

        mockMvc.perform(
                delete("/api/products/" + product.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    WebResponse<String> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){} );
                    Assertions.assertSame(false, webResponse.isError());
                    Assertions.assertEquals("Request Success!", webResponse.getMessage());
                    Assertions.assertNull(webResponse.getData());
                }
        );
    }

    @Test
    public void deleteDataIdNotFound()throws Exception{
        mockMvc.perform(
                delete("/api/products/" + "13123asdasdasd").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(
                result -> {
//                    WebResponse<String> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){} );
//                    Assertions.assertNotNull(result.getResponse().getContentAsString());
                }
        );
    }


}
