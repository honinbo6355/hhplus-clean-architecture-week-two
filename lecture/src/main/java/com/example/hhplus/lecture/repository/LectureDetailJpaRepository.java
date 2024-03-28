package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.LectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureDetailJpaRepository extends JpaRepository<LectureDetail, Long> {
}
