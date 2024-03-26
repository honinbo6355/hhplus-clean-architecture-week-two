package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.Lecture;

import java.util.Optional;

public interface LectureRepository {
    Optional<Lecture> findById(Long lectureId);
    Lecture save(Lecture lecture);
}
