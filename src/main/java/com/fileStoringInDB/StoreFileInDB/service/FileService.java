package com.fileStoringInDB.StoreFileInDB.service;

import com.fileStoringInDB.StoreFileInDB.model.FileEntity;
import com.fileStoringInDB.StoreFileInDB.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileEntity file = new FileEntity(fileName, multipartFile.getContentType(), multipartFile.getBytes());
        return fileRepository.save(file);
    }
}
