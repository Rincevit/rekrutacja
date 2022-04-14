package com.example.rekrutacja.service;



import com.example.rekrutacja.model.Teacher;
import com.example.rekrutacja.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        this.teacherRepo.save(teacher);

    }

    @Override
    public Teacher getTeacherById(long id) {
        Optional<Teacher> optional = teacherRepo.findById(id);
        Teacher teacher = null;
        if(optional.isPresent()) {
            teacher = optional.get();
        } else {
            throw new RuntimeException("Teacher not found for id: " + id);
        }
        return teacher;
    }

    @Override
    public void deleteTeacherById(long id) {
        this.teacherRepo.deleteById(id);

    }

    @Override
    public Page<Teacher> selectTeacherPage(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.teacherRepo.findAll(pageable);
    }


}
