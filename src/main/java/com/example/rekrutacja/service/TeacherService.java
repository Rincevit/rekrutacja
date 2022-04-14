package com.example.rekrutacja.service;


import com.example.rekrutacja.model.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher);
    Teacher getTeacherById(long id);
    void deleteTeacherById(long id);
    Page<Teacher> selectTeacherPage(int pageNo, int pageSize,String sortField, String sortDirection);
}

