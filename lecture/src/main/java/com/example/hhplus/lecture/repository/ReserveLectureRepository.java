package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.ReserveLecture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveLectureRepository {
    Optional<ReserveLecture> findByUserId(Long userId);
    Optional<Lecture> findById(Long lectureId);
    Lecture save(Lecture lecture);
    ReserveLecture save(ReserveLecture reserveLecture);
}
