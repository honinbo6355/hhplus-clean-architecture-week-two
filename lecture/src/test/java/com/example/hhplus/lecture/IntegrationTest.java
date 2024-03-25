package com.example.hhplus.lecture;

import com.example.hhplus.lecture.entity.Lecture;
import com.example.hhplus.lecture.repository.LectureJpaRepository;
import com.example.hhplus.lecture.repository.ReserveLectureJpaRepository;
import com.example.hhplus.lecture.service.LectureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ReserveLectureJpaRepository reserveLectureJpaRepository;
    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @Test
    @DisplayName("여러_사용자가_동시에_특강_신청할_경우")
    public void 여러_사용자가_동시에_특강_신청할_경우() throws InterruptedException {
        int userCount = 100;
//        int userCount = 1000; // 성공되는 경우도 있음
        Long lectureId = 1L;

        CountDownLatch latch = new CountDownLatch(userCount);
        ExecutorService executorService = Executors.newFixedThreadPool(userCount);

        for (int i=0; i<userCount; i++) {
            Long userId = Long.valueOf(i);
            executorService.submit(() -> {
                try {
                    lectureService.reserve(userId, lectureId);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        executorService.shutdown();
        latch.await();

        Lecture lecture = lectureJpaRepository.findById(1L)
                        .orElseThrow(NullPointerException::new);
        Assertions.assertEquals(30, lecture.getReservedCount());
    }

    public void 한_사용자가_중복_신청할_경우() {

    }
}
