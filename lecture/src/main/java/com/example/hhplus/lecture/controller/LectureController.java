package com.example.hhplus.lecture.controller;

import com.example.hhplus.lecture.common.BasicResponse;
import com.example.hhplus.lecture.dto.ReserveLectureRequestDto;
import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<BasicResponse<ReserveLectureResponseDto>> reserve(@RequestBody ReserveLectureRequestDto requestDto) {
        return new ResponseEntity<>(new BasicResponse<>(lectureService.reserve(requestDto.getUserId(), requestDto.getLectureId()), null), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<BasicResponse<ReserveStatus>> getStatus(@PathVariable Long userId) {
        return new ResponseEntity<>(new BasicResponse<>(lectureService.getStatus(userId), null), HttpStatus.OK);
    }
}
