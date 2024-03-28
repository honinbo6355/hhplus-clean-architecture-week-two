package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.entity.LectureReservedCount;
import com.example.hhplus.lecture.repository.LectureReservedCountJpaRepository;
import com.example.hhplus.lecture.repository.LectureReservedCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LectureReservedCountRepositoryImpl implements LectureReservedCountRepository {

    private final LectureReservedCountJpaRepository lectureReservedCountJpaRepository;

    @Override
    public LectureReservedCount find(Long lectureDetailId) {
        return lectureReservedCountJpaRepository.findByLectureDetailId(lectureDetailId);
    }

    @Override
    public LectureReservedCount save(LectureReservedCount lectureReservedCount) {
        return lectureReservedCountJpaRepository.save(lectureReservedCount);
    }
}
