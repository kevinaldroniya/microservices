package com.domain.expansion.productservice;

import com.domain.expansion.productservice.dto.ProductResponse;
import com.domain.expansion.productservice.dto.RequestEditProduct;
import com.domain.expansion.productservice.dto.WebResponse;
import com.domain.expansion.productservice.model.Product;
import com.domain.expansion.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UpdateProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        productRepository.deleteAll();
    }

    @Test
    public void updateTest() throws Exception {
        Product product = new Product();
        product.setName("coba");
        product.setId("11223344555");
        product.setDescription("ajdoajwiodjawidaiwjdaowijd");
        product.setPrice(BigDecimal.valueOf(102312));
        productRepository.save(product);

        RequestEditProduct requestEditProduct = new RequestEditProduct();
        requestEditProduct.setId("11223344555");
        requestEditProduct.setName("coba di edit");
        requestEditProduct.setPrice(BigDecimal.valueOf(123));
        requestEditProduct.setDescription("Ini adalah edit");

        mockMvc.perform(
                put("/api/products").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestEditProduct))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    WebResponse<ProductResponse> webResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){

                    });
                    log.info(webResponse.getData().toString());
                    Assertions.assertNotSame(product.getName(), webResponse.getData().get(0).getName());
                    Assertions.assertEquals(product.getId(), webResponse.getData().get(0).getId());
                    Assertions.assertNotSame(product.getDescription(), webResponse.getData().get(0).getDescription());
                    Assertions.assertNotSame(product.getPrice(), webResponse.getData().get(0).getPrice());
                }
        );

    }
}
