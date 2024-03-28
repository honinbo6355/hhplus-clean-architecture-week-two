package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.LectureReservedCount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureReservedCountJpaRepository extends JpaRepository<LectureReservedCount, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    LectureReservedCount findByLectureDetailId(Long lectureDetailId);
}
