package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.ReserveLecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReserveLectureJpaRepository extends JpaRepository<ReserveLecture, Long> {
    Optional<ReserveLecture> findByUserId(Long userId);
}
