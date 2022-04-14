package com.example.rekrutacja.repository;

import com.example.rekrutacja.model.Student;
import com.example.rekrutacja.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

}

