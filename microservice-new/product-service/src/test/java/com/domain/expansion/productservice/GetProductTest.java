package com.domain.expansion.productservice;

import com.domain.expansion.productservice.dto.ProductRequest;
import com.domain.expansion.productservice.dto.ProductResponse;
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
public class GetProductTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp(){
        productRepository.deleteAll();
    }

    @Test
    public void getProducts()throws  Exception{
        Product product = new Product();
        product.setId("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        product.setName("baju");
        product.setDescription("baju adalah pakaian");
        product.setPrice(BigDecimal.valueOf(100000000));
        mockMvc.perform(
                get("/api/products").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    WebResponse<ProductResponse> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                    });
                    Assertions.assertNotNull(webResponse.getData());
                }
        );
    }

    @Test
    public void getProductsNotFound() throws Exception{
        mockMvc.perform(
                get("/api/products").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(
                result -> {
                    WebResponse<ProductResponse> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                    });
                    Assertions.assertNull(webResponse.getData());
                }
        );
    }


}
