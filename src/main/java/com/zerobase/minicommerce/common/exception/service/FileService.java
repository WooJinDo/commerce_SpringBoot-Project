package com.zerobase.minicommerce.common.exception.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    public String[] uploadFile(String uploadPath, String imgOriginName, MultipartFile fileInput) throws IOException{

        String uuid = UUID.randomUUID().toString().replace("-","");
        // 확장자
        String extension = imgOriginName.substring(imgOriginName.lastIndexOf("."));
        log.info("extension: " + extension);

        // 저장 파일명 (확장자 포함)
        String imgSaveName = uuid + extension;
        log.info("savedFileName: " + imgSaveName);

        // 저장 폴더를 uploadPath 뒤에 년, 월, 일로 depth 를 생성하기 위하여
        String folderPath = makeDir(uploadPath);
        log.info("folderPath: " + folderPath);

        // 실제 파일 저장 경로
        String imgPath = uploadPath + File.separator + folderPath + File.separator + imgSaveName;

        // 하위폴더 포함 경로 (년, 월, 일 + 저장 파일명)
        String SubFolderFilePath = folderPath + File.separator + imgSaveName;

        // 실제로 로컬에 uuid를 파일명으로 저장
        fileInput.transferTo(new File(imgPath));
//        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
//        fos.write(fileData);
//        fos.close();

        String[] result = {imgSaveName, imgPath, SubFolderFilePath};
        return result;
    }

    private String makeDir(String uploadPath) {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // uploadPath 내에 yyyy/MM/dd 형식의 하위 폴더 생성
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    public void deleteFile(String imgPath) {
        File deleteFile = new File(imgPath);

        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
