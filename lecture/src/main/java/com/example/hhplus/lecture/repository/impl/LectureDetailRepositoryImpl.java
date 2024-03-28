package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.entity.LectureDetail;
import com.example.hhplus.lecture.repository.LectureDetailJpaRepository;
import com.example.hhplus.lecture.repository.LectureDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureDetailRepositoryImpl implements LectureDetailRepository {

    private final LectureDetailJpaRepository lectureDetailJpaRepository;

    @Override
    public List<LectureDetail> findAll() {
        return lectureDetailJpaRepository.findAll();
    }
}
