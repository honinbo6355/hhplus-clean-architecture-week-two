package com.example.hhplus.lecture.service;

import com.example.hhplus.lecture.dto.ReserveLectureRequestDto;
import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.exception.CustomException;
import com.example.hhplus.lecture.exception.ErrorCode;
import com.example.hhplus.lecture.repository.ReserveLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LectureService {
    private final ReserveLectureRepository reserveLectureRepository;

    @Transactional
    public ReserveLectureResponseDto reserve(Long userId, Long lectureId) {
        final int MAX_RESERVE_SIZE = 30;

        ReserveLecture reserveLecture = reserveLectureRepository.findByUserId(userId)
                .orElse(null);
        if (reserveLecture != null) {
            throw new CustomException(ErrorCode.ALREADY_RESERVED_USER);
        }

        Lecture lecture = reserveLectureRepository.findById(lectureId)
                .orElseThrow(NullPointerException::new);

        if (lecture.getReservedCount() >= MAX_RESERVE_SIZE) {
            throw new CustomException(ErrorCode.ALREADY_MAX_RESERVED_SIZE);
        }
        lecture.increaseReservedCount();

        reserveLectureRepository.save(lecture);
        return new ReserveLectureResponseDto(reserveLectureRepository.save(new ReserveLecture(userId, lectureId)));
    }

    @Transactional
    public ReserveStatus getStatus(Long userId) {
        ReserveLecture reserveLecture = reserveLectureRepository.findByUserId(userId)
                .orElse(null);
        if (reserveLecture != null) {
            return ReserveStatus.SUCCESS;
        }

        return ReserveStatus.FAIL;
    }
}
