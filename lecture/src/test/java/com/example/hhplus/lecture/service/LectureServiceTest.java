package com.example.hhplus.lecture.service;

import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.repository.LectureRepository;
import com.example.hhplus.lecture.repository.ReserveLectureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {
    @Mock
    private ReserveLectureRepository reserveLectureRepository;
    @Mock
    private LectureRepository lectureRepository;
    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("행사_신청_성공할경우")
    public void 행사_신청_성공할경우() {
        Long userId = 1L;
        Long lectureId = 1L;

        Optional<Lecture> lectureOptional = Optional.of(new Lecture(LocalDateTime.of(2024, 04, 20, 13, 0, 0), 0));

        when(reserveLectureRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(lectureRepository.findById(lectureId)).thenReturn(lectureOptional);
        when(reserveLectureRepository.save(any())).thenReturn(new ReserveLecture(1L, userId, lectureId));

        ReserveLectureResponseDto reserveLectureResponseDto = lectureService.reserve(userId, lectureId);

        Assertions.assertEquals(userId, reserveLectureResponseDto.getUserId());
        Assertions.assertEquals(lectureId, reserveLectureResponseDto.getLectureId());
    }

    @Test
    @DisplayName("이미_행사_신청한경우")
    public void 이미_행사_신청한경우() {

    }

    @Test
    @DisplayName("이미_정원_마감했을경우")
    public void 이미_정원_마감했을경우() {

    }

    @Test
    @DisplayName("신청한_유저_조회할경우")
    public void 신청한_유저_조회할경우() {

    }

    @Test
    @DisplayName("신청하지않은_유저_조회할경우")
    public void 신청하지않은_유저_조회할경우() {

    }
}
