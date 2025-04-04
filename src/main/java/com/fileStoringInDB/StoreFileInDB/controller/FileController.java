package com.fileStoringInDB.StoreFileInDB.controller;

import com.fileStoringInDB.StoreFileInDB.constants.AppConstants;
import com.fileStoringInDB.StoreFileInDB.message.ResponseMessage;
import com.fileStoringInDB.StoreFileInDB.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

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

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable UUID id){
        String message = "";
        ResponseMessage resp = new ResponseMessage(message);
        try{
            message = "File deleted successfully";
            resp.setMessage(message);
            fileService.deleteFile(id);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e){
            message = "Failed to delete";
            resp.setMessage(message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @GetMapping("/getFile/{id}")
    public ResponseEntity<ResponseMessage> getFile(@PathVariable UUID id){
        String message = "";
        ResponseMessage resp = new ResponseMessage(message);
        try{
            message = "File retrieved successfully";
            resp.setMessage(message);
            fileService.getFile(AppConstants.fileStoringPath+"/retrievedFile_"+id.toString(), id);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            message = "Failed to get the file";
            resp.setMessage(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
    }
}
