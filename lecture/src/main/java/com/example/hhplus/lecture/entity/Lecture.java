package com.example.hhplus.lecture.entity;

import com.example.hhplus.lecture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(schema = "test", name = "lecture")
@Getter
@NoArgsConstructor
public class Lecture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startsAt;
    private long reservedCount;

    public Lecture(LocalDateTime startsAt, long reservedCount) {
        this.startsAt = startsAt;
        this.reservedCount = reservedCount;
    }

    public void increaseReservedCount() {
        reservedCount += 1;
    }
}
