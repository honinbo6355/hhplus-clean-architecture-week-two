package com.example.hhplus.lecture.entity;

import com.example.hhplus.lecture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "test", name = "lecture_reserved_count")
@Getter
@NoArgsConstructor
public class LectureReservedCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lectureDetailId;
    private long count = 0;
    
    public LectureReservedCount(Long id, Long lectureDetailId, long count) {
        this.id = id;
        this.lectureDetailId = lectureDetailId;
        this.count = count;
    }
    public boolean isFinishedReserve(final int MAX_RESERVE_SIZE) {
        return count >= MAX_RESERVE_SIZE;
    }
    public void increaseCount() {
        count += 1;
    }

    public LectureReservedCount(Long lectureDetailId, long count) {
        this.lectureDetailId = lectureDetailId;
        this.count = count;
    }
}
