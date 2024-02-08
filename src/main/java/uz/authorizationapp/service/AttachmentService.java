package uz.authorizationapp.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.authorizationapp.entity.Attachment;
import uz.authorizationapp.repository.AttachmentRepository;
import uz.authorizationapp.upload.ApiResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    public ApiResponse addAttachment(MultipartFile request) throws IOException {
        MultipartFile file = request;
        if(file != null){
            Attachment files = new Attachment();
            files.setContentType(file.getContentType());
            files.setSize(file.getSize());
            files.setOriginalName(file.getOriginalFilename());

            String uuid = UUID.randomUUID().toString();
            String[] filetype = files.getOriginalName().split("\\.");
            String uri = uuid + "." + filetype[filetype.length - 1];
            files.setName(uri);
            attachmentRepository.save(files);
            Path path = Paths.get("medias/" + uri);
            java.nio.file.Files.copy(file.getInputStream(),path);
            return new ApiResponse(true,"File muvaffaqiyatli saqlandi");
        }
        return new ApiResponse(false,"File yuklanmadi");
    }

//    public ApiResponse getFileFolder(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> filesOptional = attachmentRepository.findById(id);
//        if (filesOptional.isPresent()) {
//            Attachment files = filesOptional.get();
//            response.setHeader(
//                    "Content-Disposition", "attachment; filename=\"" + files.getName() + "\"");
//            response.setContentType(files.getContentType());
//
//            FileInputStream fileInputStream = new FileInputStream("medias/" + files.getName());
//
//            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
//
//        }
//        return new ApiResponse(false,"file not found");
//    }

    public ResponseEntity<?> getFileFolder(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> filesOptional = attachmentRepository.findById(id);
        if (filesOptional.isPresent()) {
            Attachment files = filesOptional.get();
            File file = new File("medias/" + files.getName());
            if (file.exists()) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getName() + "\"");
                response.setContentType(files.getContentType());
                response.setHeader("Accept", files.getContentType());

                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    FileCopyUtils.copy(fileInputStream, response.getOutputStream());
                    return ResponseEntity.ok().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
