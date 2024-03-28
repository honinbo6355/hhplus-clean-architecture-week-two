package com.example.hhplus.lecture.repository.impl;

import com.example.hhplus.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
}
