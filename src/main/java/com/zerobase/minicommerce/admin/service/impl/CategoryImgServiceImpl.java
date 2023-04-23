package com.zerobase.minicommerce.admin.service.impl;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.admin.domain.CategoryImg;
import com.zerobase.minicommerce.admin.repository.CategoryImgRepository;
import com.zerobase.minicommerce.admin.service.CategoryImgService;
import com.zerobase.minicommerce.common.exception.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryImgServiceImpl implements CategoryImgService {

    @Value("${categoryImgLocation}")
    private String categoryImgLocation;

    private final CategoryImgRepository categoryImgRepository;

    private final FileService fileService;

    /* 카테고리 이미지 저장 */
    @Override
    public void adminSaveCategoryImg(Category category, MultipartFile fileInput) throws IOException {
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
            filePath = fileService.uploadFile(categoryImgLocation, imgOriginName, fileInput);
            imgSaveName = filePath[0];
            imgPath = filePath[1];
            SubFolderFilePath = filePath[2];

            imgUrlPath = File.separator + "img" + File.separator + "category" + File.separator + SubFolderFilePath;
        }

        log.info("imgUrlPath: " + imgUrlPath);

        //DB insert
        CategoryImg saveCategoryImg = CategoryImg.builder()
                .imgOriginName(imgOriginName)
                .imgSaveName(imgSaveName)
                .imgPath(imgPath)
                .imgUrlPath(imgUrlPath)
                .categoryId(category.getCategoryId())
                .createdAt(LocalDateTime.now())
                .build();
        categoryImgRepository.save(saveCategoryImg);
    }
}
