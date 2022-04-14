package com.example.rekrutacja.service;

import com.example.rekrutacja.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(long id);
    void deleteStudentById(long id);
    Page<Student> selectPage(int pageNo, int pageSize, String sortField, String sortDirection);
}
