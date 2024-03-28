package com.example.hhplus.lecture.repository;

import com.example.hhplus.lecture.entity.LectureDetail;

import java.util.List;

public interface LectureDetailRepository {
    List<LectureDetail> findAll();
}
