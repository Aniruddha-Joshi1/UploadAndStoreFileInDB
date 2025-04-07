package com.fileStoringInDB.StoreFileInDB.service;

import com.fileStoringInDB.StoreFileInDB.model.FileEntity;
import com.fileStoringInDB.StoreFileInDB.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        try{
            String fileName = multipartFile.getOriginalFilename();
            FileEntity file = new FileEntity(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            return fileRepository.save(file);
        } catch(IOException e) {
            throw new IOException("failed to save the file - " + multipartFile.getOriginalFilename());
        }
    }

    public void deleteFile(UUID id) {
        try {
            fileRepository.deleteById(id);
        } catch (Exception e){
            System.out.println("Error occured while deleting file - " + e.getMessage());
        }
    }

    public void getFile(String filePath, UUID id) throws IOException{
        File file = new File(filePath);
        // try with resources (closes the file automatically - check medium article)
        try(FileOutputStream outputFile = new FileOutputStream(file);){
            Optional<FileEntity> fileEntity = fileRepository.findById(id);
            if(fileEntity.isPresent()) {
                byte[] fileData = fileEntity.get().getData();
                outputFile.write(fileData);
            } else {
                throw new RuntimeException("File not found with ID: " + id);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
