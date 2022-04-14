package com.example.rekrutacja.controller;

import com.example.rekrutacja.model.Student;
import com.example.rekrutacja.model.Teacher;
import com.example.rekrutacja.service.StudentService;
import com.example.rekrutacja.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    //display list of students
    @GetMapping("/students")
    public String vieHomePage(Model model) {
       return findPage(1,"name", "asc", model);
    }
    @GetMapping("/newStudentForm")
    public String showStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);

        return "new_student";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormFormUpdate(@PathVariable(value = "id") long id, Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "update_student";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {

        this.studentService.deleteStudentById(id);

        return "redirect:/students";
    }
    @RequestMapping("/page/{pageNo}")
    public String findPage(@PathVariable (value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;

        Page<Student> page = studentService.selectPage(pageNo, pageSize, sortField, sortDir);
        List<Student> studentList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField", sortField );
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStudents", studentList);

        return "students";
    }



}
