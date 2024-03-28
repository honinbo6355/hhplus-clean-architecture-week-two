package com.example.hhplus.lecture.entity;

import com.example.hhplus.lecture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(schema = "test", name = "lecture_detail")
@Getter
@NoArgsConstructor
public class LectureDetail extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lectureId;
    private LocalDateTime startsAt;

    public LectureDetail(Long id, Long lectureId, LocalDateTime startsAt) {
        this.id = id;
        this.lectureId = lectureId;
        this.startsAt = startsAt;
    }
}
