package com.domain.expansion.productservice.controller;

import com.domain.expansion.productservice.dto.ProductRequest;
import com.domain.expansion.productservice.dto.ProductResponse;
import com.domain.expansion.productservice.dto.RequestEditProduct;
import com.domain.expansion.productservice.dto.WebResponse;
import com.domain.expansion.productservice.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<String> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return WebResponse.<String>builder().status(HttpStatus.CREATED).error(false).message("Request Success!").build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductResponse> getAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
//        if(products.size() < 0){
//            return WebResponse.<ProductResponse>builder().data(null).error(true).message("Data Not Found").status(HttpStatus.NOT_FOUND).build();
//        }
        return WebResponse.<ProductResponse>builder().data(products).error(false).message("Request Success!").status(HttpStatus.OK).build();
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return WebResponse.<String>builder().status(HttpStatus.OK).error(false).message("Request Success!").build();
    }


    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> updateProduct(@RequestBody RequestEditProduct product){
        List<ProductResponse> productResponses = new ArrayList<>(1);
        productResponses.add(productService.updateProduct(product));
        return WebResponse.<ProductResponse>builder().data(productResponses).error(false).message("Request Success!").status(HttpStatus.OK).build();
    }


}
