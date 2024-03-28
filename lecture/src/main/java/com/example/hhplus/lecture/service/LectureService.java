package com.example.hhplus.lecture.service;

import com.example.hhplus.lecture.dto.LectureDto;
import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.LectureDetail;
import com.example.hhplus.lecture.entity.LectureReservedCount;
import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.exception.CustomException;
import com.example.hhplus.lecture.exception.ErrorCode;
import com.example.hhplus.lecture.repository.LectureDetailRepository;
import com.example.hhplus.lecture.repository.LectureRepository;
import com.example.hhplus.lecture.repository.LectureReservedCountRepository;
import com.example.hhplus.lecture.repository.ReserveLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LectureService {
    final int MAX_RESERVE_SIZE = 30;

    private final ReserveLectureRepository reserveLectureRepository;
    private final LectureReservedCountRepository lectureReservedCountRepository;
    private final LectureDetailRepository lectureDetailRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public ReserveLectureResponseDto reserve(Long userId, Long lectureDetailId) {
        ReserveLecture reserveLecture = reserveLectureRepository.find(userId, lectureDetailId)
                .orElse(null);
        if (reserveLecture != null) {
            throw new CustomException(ErrorCode.ALREADY_RESERVED_USER);
        }
        LectureReservedCount lectureReservedCount = lectureReservedCountRepository.find(lectureDetailId);

        if (lectureReservedCount.isFinishedReserve(MAX_RESERVE_SIZE)) {
            throw new CustomException(ErrorCode.ALREADY_MAX_RESERVED_SIZE);
        }
        lectureReservedCount.increaseCount();

        lectureReservedCountRepository.save(lectureReservedCount);

        return new ReserveLectureResponseDto(reserveLectureRepository.save(new ReserveLecture(userId, lectureDetailId)));
    }

    @Transactional(readOnly = true)
    public ReserveStatus getReserveStatus(Long lectureDetailId, Long userId) {
        ReserveLecture reserveLecture = reserveLectureRepository.find(userId, lectureDetailId)
                .orElse(null);
        if (reserveLecture != null) {
            return ReserveStatus.SUCCESS;
        }

        return ReserveStatus.FAIL;
    }

    @Transactional(readOnly = true)
    public List<LectureDto> getAvailableLectures(Long userId) {
        // 신청한 행사 리스트 아이디 추출
        List<Long> reservedLectureDetailIds = reserveLectureRepository.findList(userId)
                .stream()
                .map(ReserveLecture::getLectureDetailId)
                .collect(Collectors.toList());

        // 모든 행사 상세 정보 조회
        List<LectureDetail> lectureDetails = lectureDetailRepository.findAll();

        // 이미 신청한 행사를 제외한 리스트 추출
        List<LectureDetail> filteredLectureDetails = lectureDetails.stream()
                .filter(lectureDetail -> !reservedLectureDetailIds.contains(lectureDetail.getId()))
                .collect(Collectors.toList());

        List<LectureDto> lectureDtos = new ArrayList<>();

        filteredLectureDetails.stream()
                .forEach(lectureDetail -> {
                    // 행사 신청 카운트 조회
                    LectureReservedCount lectureReservedCount = lectureReservedCountRepository.find(lectureDetail.getId());

                    // 이미 마감했으면 제외
                    if (lectureReservedCount.isFinishedReserve(MAX_RESERVE_SIZE)) {
                        return;
                    }
                    Lecture lecture = lectureRepository.find(lectureDetail.getLectureId())
                            .orElseThrow(NullPointerException::new);
                    lectureDtos.add(new LectureDto(lecture, lectureDetail, lectureReservedCount));
                });

        return lectureDtos;
    }
}
