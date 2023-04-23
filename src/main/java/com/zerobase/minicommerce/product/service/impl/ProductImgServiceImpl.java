package com.zerobase.minicommerce.product.service.impl;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.admin.domain.CategoryImg;
import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.admin.repository.CategoryImgRepository;
import com.zerobase.minicommerce.admin.repository.CategoryRepository;
import com.zerobase.minicommerce.admin.service.CategoryImgService;
import com.zerobase.minicommerce.admin.service.CategoryService;
import com.zerobase.minicommerce.common.exception.service.FileService;
import com.zerobase.minicommerce.product.domain.Product;
import com.zerobase.minicommerce.product.domain.ProductImg;
import com.zerobase.minicommerce.product.repository.ProductImgRepository;
import com.zerobase.minicommerce.product.service.ProductImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImgServiceImpl implements ProductImgService {

    @Value("${productImgLocation}")
    private String productImgLocation;

    private final ProductImgRepository productImgRepository;

    private final FileService fileService;

    // 상품 이미지 등록
    @Override
    public void saveProductImg(Product product, MultipartFile fileInput) throws IOException {
        String imgOriginName = fileInput.getOriginalFilename(); // 원본 파일명

        String[] filePath = null;

        String imgSaveName = ""; // 저장 파일명(uuid 적용)
        String imgPath = ""; //실제 파일 저장 경로
        String imgUrlPath = ""; // WebConfig 소스 적용된 url
        String SubFolderFilePath = ""; // 하위폴더 포함 경로 (년, 월, 일 + 저장 파일명)

        // 파일 업로드
        if (!StringUtils.isEmpty(imgOriginName)) {

            /*
             * [0] 확장자명 포함 저장 파일명
             * [1] 실제 파일 적재 경로
             * [2] 하위폴더 포함한 경로 (yyyy/MM/dd / 파일명.확장자)
             * */
            filePath = fileService.uploadFile(productImgLocation, imgOriginName, fileInput);
            imgSaveName = filePath[0];
            imgPath = filePath[1];
            SubFolderFilePath = filePath[2];

            imgUrlPath = File.separator + "img" + File.separator + "product" + File.separator + SubFolderFilePath;
        }

        log.info("imgSaveName: " + imgSaveName);
        log.info("imgPath: " + imgPath);
        log.info("imgUrlPath: " + imgUrlPath);

        //DB insert
        productImgRepository.save(
                ProductImg.builder()
                        .imgOriginName(imgOriginName)
                        .imgSaveName(imgSaveName)
                        .imgPath(imgPath)
                        .imgUrlPath(imgUrlPath)
                        .productId(product.getProductId())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

    }

    // 상품 이미지 수정
    @Override
    public void updateProductImg(Long imgId, MultipartFile fileInput) throws IOException {
        String imgOriginName = ""; // 원본 파일명

        String[] filePath = null;

        String imgSaveName = ""; // 저장 파일명(uuid 적용)
        String imgPath = ""; //실제 파일 저장 경로
        String imgUrlPath = ""; // WebConfig 소스 적용된 url
        String SubFolderFilePath = ""; // 하위폴더 포함 경로 (년, 월, 일 + 저장 파일명)

        // 상품 이미지를 수정했다면
        if (!fileInput.isEmpty()) {
            ProductImg saveProductImg = productImgRepository.findById(imgId).orElseThrow(EntityNotFoundException::new);

            // 기존 이미지 파일이 존재한다면 삭제
            if (!StringUtils.isEmpty(saveProductImg.getImgSaveName())) {
                fileService.deleteFile(saveProductImg.getImgPath());
            }

            imgOriginName = fileInput.getOriginalFilename();
            /*
             * [0] 확장자명 포함 저장 파일명
             * [1] 실제 파일 적재 경로
             * [2] 하위폴더 포함한 경로 (yyyy/MM/dd / 파일명.확장자)
             * */
            filePath = fileService.uploadFile(productImgLocation,
                    Objects.requireNonNull(imgOriginName, "파일 저장명이 존재하지 않습니다."), fileInput);
            imgSaveName = filePath[0];
            imgPath = filePath[1];
            SubFolderFilePath = filePath[2];

            imgUrlPath = File.separator + "images" + File.separator + "product" + File.separator + SubFolderFilePath;

//            saveProductImg.setImgOriginName(imgOriginName);
//            saveProductImg.setImgSaveName(imgSaveName);
//            saveProductImg.setImgPath(imgPath);
//            saveProductImg.setImgUrlPath(imgUrlPath);
//            saveProductImg.setUpdatedAt(LocalDateTime.now());

            // 관리자 상품 이미지 DB update
            saveProductImg.updateProductImg(imgOriginName, imgSaveName, imgPath, imgUrlPath);
        }


    }
}
