package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.exception.CustomException;
import com.example.hhplus.lecture.exception.ErrorCode;
import com.example.hhplus.lecture.repository.LectureJpaRepository;
import com.example.hhplus.lecture.repository.ReserveLectureJpaRepository;
import com.example.hhplus.lecture.repository.ReserveLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReserveLectureRepositoryImpl implements ReserveLectureRepository {

    private final ReserveLectureJpaRepository reserveLectureJpaRepository;

    public Optional<ReserveLecture> findByUserId(Long userId) {
        return reserveLectureJpaRepository.findByUserId(userId);
    }

    public ReserveLecture save(ReserveLecture reserveLecture) {
        return reserveLectureJpaRepository.save(reserveLecture);
    }
}
