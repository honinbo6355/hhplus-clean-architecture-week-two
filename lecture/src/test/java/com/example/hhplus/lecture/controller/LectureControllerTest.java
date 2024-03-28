package com.example.hhplus.lecture.controller;

import com.example.hhplus.lecture.dto.LectureDto;
import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.entity.LectureDetail;
import com.example.hhplus.lecture.entity.LectureReservedCount;
import com.example.hhplus.lecture.entity.ReserveLecture;
import com.example.hhplus.lecture.exception.CustomException;
import com.example.hhplus.lecture.exception.ErrorCode;
import com.example.hhplus.lecture.service.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LectureController.class)
public class LectureControllerTest {

    @MockBean
    private LectureService lectureService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("특강_신청_성공할경우")
    public void 특강_신청_성공할경우() throws Exception {
        // given
        Long userId = 1L;
        Long lectureDetailId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureDetailId\":" + lectureDetailId + "}";

        // when
        ReserveLectureResponseDto reserveLectureResponseDto = new ReserveLectureResponseDto(new ReserveLecture(1L, lectureDetailId));
        when(lectureService.reserve(userId, lectureDetailId)).thenReturn(reserveLectureResponseDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lectures")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.userId").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.lectureDetailId").value(lectureDetailId));
    }

    @Test
    @DisplayName("정원_초과_로_특강_신청_실패할경우")
    public void 정원_초과_로_특강_신청_실패할경우() throws Exception {
        // given
        Long userId = 1L;
        Long lectureDetailId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureDetailId\":" + lectureDetailId + "}";

        // when
        when(lectureService.reserve(userId, lectureDetailId)).thenThrow(new CustomException(ErrorCode.ALREADY_MAX_RESERVED_SIZE));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lectures")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.code").value(ErrorCode.ALREADY_MAX_RESERVED_SIZE.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.message").value(ErrorCode.ALREADY_MAX_RESERVED_SIZE.getMessage()));
    }

    @Test
    @DisplayName("중복_신청_으로_특강_신청_실패할경우")
    public void 중복_신청_으로_특강_신청_실패할경우() throws Exception {
        // given
        Long userId = 1L;
        Long lectureDetailId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureDetailId\":" + lectureDetailId + "}";

        // when
        when(lectureService.reserve(userId, lectureDetailId)).thenThrow(new CustomException(ErrorCode.ALREADY_RESERVED_USER));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lectures")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.code").value(ErrorCode.ALREADY_RESERVED_USER.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.message").value(ErrorCode.ALREADY_RESERVED_USER.getMessage()));
    }

    @Test
    @DisplayName("특강_신청_했을경우_조회시")
    public void 특강_신청_했을경우_조회시() throws Exception {
        // given
        Long userId = 1L;
        Long lectureDetailId = 1L;

        // when
        when(lectureService.getReserveStatus(lectureDetailId, userId)).thenReturn(ReserveStatus.SUCCESS);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lectures/" + lectureDetailId + "/" + "users/" + userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(ReserveStatus.SUCCESS.toString()));
    }

    @Test
    @DisplayName("특강_신청_하지않은경우_조회시")
    public void 특강_신청_하지않은경우_조회시() throws Exception {
        // given
        Long userId = 1L;
        Long lectureDetailId = 1L;

        // when
        when(lectureService.getReserveStatus(lectureDetailId, userId)).thenReturn(ReserveStatus.FAIL);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lectures/" + lectureDetailId + "/" + "users/" + userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(ReserveStatus.FAIL.toString()));
    }

    @Test
    @DisplayName("특강_신청_목록_조회시")
    public void 특강_신청_목록_조회시() throws Exception {
        // given
        Long userId = 1L;
        List<LectureDto> lectureDtoList = new ArrayList<>();
        lectureDtoList.add(new LectureDto(
                new Lecture(1L, "유리상자 콘서트"),
                new LectureDetail(1L, 1L, LocalDateTime.of(2024, 2, 15, 13, 0)),
                new LectureReservedCount(1L, 1L, 10))
        );
        lectureDtoList.add(new LectureDto(
                new Lecture(2L, "황영웅 대전콘서트"),
                new LectureDetail(2L, 2L, LocalDateTime.of(2024, 1, 13, 11, 0)),
                new LectureReservedCount(2L, 2L, 0))
        );
        lectureDtoList.add(new LectureDto(
                new Lecture(3L, "빌드업 콘서트"),
                new LectureDetail(3L, 3L, LocalDateTime.of(2024, 3, 9, 10, 0)),
                new LectureReservedCount(3L, 3L, 20))
        );

        // when
        when(lectureService.getAvailableLectures(userId)).thenReturn(lectureDtoList);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lectures/users/" + userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.size()").value(3));
    }
}
