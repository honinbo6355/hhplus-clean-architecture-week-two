package com.example.hhplus.lecture.entity;

import com.example.hhplus.lecture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "test", name = "reserve_lecture")
@Getter
@NoArgsConstructor
public class ReserveLecture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long lectureId;

    public ReserveLecture(Long userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public ReserveLecture(Long id, Long userId, Long lectureId) {
        this.id = id;
        this.userId = userId;
        this.lectureId = lectureId;
    }
}
