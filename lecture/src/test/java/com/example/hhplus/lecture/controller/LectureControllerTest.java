package com.example.hhplus.lecture.controller;

import com.example.hhplus.lecture.dto.ReserveLectureResponseDto;
import com.example.hhplus.lecture.dto.ReserveStatus;
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
        Long lectureId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureId\":" + lectureId + "}";

        // when
        ReserveLectureResponseDto reserveLectureResponseDto = new ReserveLectureResponseDto(new ReserveLecture(1L, userId));
        when(lectureService.reserve(userId, lectureId)).thenReturn(reserveLectureResponseDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lecture")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.userId").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.lectureId").value(lectureId));
    }

    @Test
    @DisplayName("정원_초과_로_특강_신청_실패할경우")
    public void 정원_초과_로_특강_신청_실패할경우() throws Exception {
        // given
        Long userId = 1L;
        Long lectureId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureId\":" + lectureId + "}";

        // when
        when(lectureService.reserve(userId, lectureId)).thenThrow(new CustomException(ErrorCode.ALREADY_MAX_RESERVED_SIZE));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lecture")
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
        Long lectureId = 1L;
        String requestJson = "{\"userId\":" + userId + ", \"lectureId\":" + lectureId + "}";

        // when
        when(lectureService.reserve(userId, lectureId)).thenThrow(new CustomException(ErrorCode.ALREADY_RESERVED_USER));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lecture")
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

        // when
        when(lectureService.getStatus(userId)).thenReturn(ReserveStatus.SUCCESS);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lecture/" + userId)
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

        // when
        when(lectureService.getStatus(userId)).thenReturn(ReserveStatus.FAIL);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lecture/" + userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(ReserveStatus.FAIL.toString()));
    }
}
