package com.zerobase.minicommerce.admin.controller.api;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.admin.service.CategoryService;
import com.zerobase.minicommerce.product.dto.ProductDto;
import com.zerobase.minicommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/*
* 관리자 공통 API
* */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiAdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    /* 관리자 - 카테고리 등록 */
    @PostMapping("/api/admin/addCategory")
    public ResponseEntity<?> addCategory(
            @RequestPart("data") @Valid CategoryDto.Request request,
            @RequestPart(value = "fileInput", required = true) MultipartFile fileInput) throws Exception {
        CategoryDto.Response response = categoryService.adminSaveCategory(request, fileInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 관리자 - 상품 등록 */
    @PostMapping("/api/admin/addProduct")
    public ResponseEntity<?> addProduct(
            @RequestPart("data") @Valid ProductDto.Request request,
            @RequestPart(value = "fileInput", required = true) MultipartFile fileInput) throws Exception {
        ProductDto.Response response = productService.saveProduct(request, fileInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 관리자 - 상품 수정 */
    @PutMapping("/api/admin/updateProduct")
    public ResponseEntity<?> updateProduct(
            @RequestPart(value = "data") @Valid ProductDto.UpdateRequest request,
            @RequestPart(value="fileInput", required=false)  MultipartFile fileInput) throws Exception {
        ProductDto.UpdateResponse response = null;
        if (request != null && fileInput == null) {
            response = productService.updateProduct(request, null);
        } else {
            response = productService.updateProduct(request, fileInput);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
