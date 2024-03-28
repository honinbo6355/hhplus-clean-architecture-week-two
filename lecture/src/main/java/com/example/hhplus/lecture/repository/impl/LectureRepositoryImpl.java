package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Optional<Lecture> find(Long lectureId) {
        return lectureJpaRepository.findById(lectureId);
    }
}
