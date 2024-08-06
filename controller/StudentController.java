package com.nducmd.dynamicjpa.controller;

import com.nducmd.dynamicjpa.entity.ResponseObject;
import com.nducmd.dynamicjpa.entity.Student;
import com.nducmd.dynamicjpa.service.StudentService;
import com.nducmd.dynamicjpa.specification.StudentSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/search")
    public ResponseEntity<ResponseObject> getStudents(
            @RequestBody StudentSearchCriteria criteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") int mode) { // mode = 0: search equal; mode = 1: search like

        Page<Student> studentPage = studentService.getStudents(criteria, page, size, sortDirection, sortBy, mode);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Tìm sinh viên thành công", studentPage));
    }
}
