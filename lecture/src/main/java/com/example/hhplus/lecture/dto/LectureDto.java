package com.example.hhplus.lecture.dto;

import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.LectureDetail;
import com.example.hhplus.lecture.entity.LectureReservedCount;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureDto {
    private Long lectureDetailId;
    private String name;
    private LocalDateTime startsAt;
    private long reservedCount = 0;

    public LectureDto(Lecture lecture, LectureDetail lectureDetail, LectureReservedCount lectureReservedCount) {
        this.lectureDetailId = lectureDetail.getId();
        this.name = lecture.getName();
        this.startsAt = lectureDetail.getStartsAt();
        this.reservedCount = lectureReservedCount.getCount();
    }
}
