package uz.authorizationapp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.authorizationapp.service.ListService;
import uz.authorizationapp.upload.ApiResponse;
import uz.authorizationapp.upload.ListDto;

@RestController
@RequestMapping("api/lists")
@AllArgsConstructor
public class ListController {
    private final ListService listService;
    @PostMapping("/create")
    public HttpEntity<?> createList(@Valid @RequestBody ListDto lists) {
        ApiResponse list = listService.createList(lists);
        return ResponseEntity.status(list.isSuccess()?201:400).body(list);
    }
}
