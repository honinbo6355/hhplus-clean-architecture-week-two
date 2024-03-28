package com.example.hhplus.lecture.controller;

import com.example.hhplus.lecture.common.BasicResponse;
import com.example.hhplus.lecture.dto.LectureDto;
import com.example.hhplus.lecture.dto.ReserveLectureRequestDto;
import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<BasicResponse<ReserveLectureResponseDto>> reserve(@RequestBody ReserveLectureRequestDto requestDto) {
        return new ResponseEntity<>(new BasicResponse<>(lectureService.reserve(requestDto.getUserId(), requestDto.getLectureDetailId()), null), HttpStatus.CREATED);
    }

    @GetMapping("{lectureDetailId}/users/{userId}")
    public ResponseEntity<BasicResponse<ReserveStatus>> getReserveStatus(@PathVariable Long lectureDetailId, @PathVariable Long userId) {
        return new ResponseEntity<>(new BasicResponse<>(lectureService.getReserveStatus(lectureDetailId, userId), null), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<BasicResponse<List<LectureDto>>> getAvailableLectures(@PathVariable Long userId) {
        return new ResponseEntity<>(new BasicResponse<>(lectureService.getAvailableLectures(userId), null), HttpStatus.OK);
    }
}
