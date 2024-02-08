package uz.authorizationapp.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.authorizationapp.service.AttachmentService;
import uz.authorizationapp.upload.ApiResponse;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/file/")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;


    @PostMapping(path = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse addAttachment(MultipartHttpServletRequest request,@RequestPart("file") MultipartFile file) throws IOException {
        ApiResponse result = attachmentService.addAttachment(file);
        return result;

    }

    @GetMapping("download/{id}")
    public HttpEntity<?> getAttachment(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ResponseEntity<?> fileFolder = attachmentService.getFileFolder(id, response);
        return fileFolder;
    }


}
