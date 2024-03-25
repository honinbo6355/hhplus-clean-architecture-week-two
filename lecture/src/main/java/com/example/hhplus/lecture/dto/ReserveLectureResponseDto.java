package com.example.hhplus.lecture.dto;

import com.example.hhplus.lecture.entity.ReserveLecture;
import lombok.Getter;

@Getter
public class ReserveLectureResponseDto {
    private Long id;
    private Long userId;
    private Long lectureId;

    public ReserveLectureResponseDto(ReserveLecture reserveLecture) {
        this.id = reserveLecture.getId();
        this.userId = reserveLecture.getUserId();
        this.lectureId = reserveLecture.getLectureId();
    }
}
