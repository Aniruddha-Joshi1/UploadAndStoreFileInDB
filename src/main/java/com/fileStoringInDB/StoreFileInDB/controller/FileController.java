package com.fileStoringInDB.StoreFileInDB.controller;

import com.fileStoringInDB.StoreFileInDB.message.ResponseMessage;
import com.fileStoringInDB.StoreFileInDB.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/saveFile")
    public ResponseEntity<ResponseMessage> saveFile(@RequestParam("file")MultipartFile file){
        String message = "";
        try{
            fileService.saveFile(file);
            message = "Uploaded " + file.getOriginalFilename() + " successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e){
            message = "uploading failed";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        }
    }
}
