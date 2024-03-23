package com.example.hhplus.lecture.controller;

import com.example.hhplus.lecture.common.BasicResponse;
import com.example.hhplus.lecture.dto.ReserveStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {

    @PostMapping
    public ResponseEntity<BasicResponse<ReserveStatus>> reserve() {
        System.out.println("reserve 메소드 실행");
        return new ResponseEntity<>(new BasicResponse<>(ReserveStatus.SUCCESS, null), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public void getStatus(@PathVariable Long userId) {

    }
}
