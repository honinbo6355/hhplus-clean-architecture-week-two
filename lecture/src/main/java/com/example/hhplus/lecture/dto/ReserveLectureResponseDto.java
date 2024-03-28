package com.example.hhplus.lecture.dto;

import com.example.hhplus.lecture.entity.ReserveLecture;
import lombok.Getter;

@Getter
public class ReserveLectureResponseDto {
    private Long id;
    private Long userId;
    private Long lectureDetailId;

    public ReserveLectureResponseDto(ReserveLecture reserveLecture) {
        this.id = reserveLecture.getId();
        this.userId = reserveLecture.getUserId();
        this.lectureDetailId = reserveLecture.getLectureDetailId();
    }
}
