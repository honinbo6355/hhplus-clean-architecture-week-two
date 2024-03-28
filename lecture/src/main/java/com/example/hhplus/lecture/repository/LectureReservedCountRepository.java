package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.LectureReservedCount;

public interface LectureReservedCountRepository {
    LectureReservedCount find(Long lectureDetailId);
    LectureReservedCount save(LectureReservedCount lectureReservedCount);
}
