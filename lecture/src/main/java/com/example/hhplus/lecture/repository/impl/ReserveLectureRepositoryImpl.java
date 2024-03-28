package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.repository.ReserveLectureJpaRepository;
import com.example.hhplus.lecture.repository.ReserveLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReserveLectureRepositoryImpl implements ReserveLectureRepository {

    private final ReserveLectureJpaRepository reserveLectureJpaRepository;

    @Override
    public Optional<ReserveLecture> find(Long userId, Long lectureDetailId) {
        return reserveLectureJpaRepository.findByUserIdAndLectureDetailId(userId, lectureDetailId);
    }

    @Override
    public ReserveLecture save(ReserveLecture reserveLecture) {
        return reserveLectureJpaRepository.save(reserveLecture);
    }

    @Override
    public List<ReserveLecture> findReservedLectures(Long userId) {
        return reserveLectureJpaRepository.findByUserId(userId);
    }
}
