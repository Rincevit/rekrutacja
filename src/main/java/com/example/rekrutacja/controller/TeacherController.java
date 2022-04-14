package com.example.rekrutacja.controller;


import com.example.rekrutacja.model.Teacher;
import com.example.rekrutacja.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //display list of teachers
    @GetMapping("/teachers")
    public String vieHomePage(Model model) {

        return findTeacherPage(1,"name", "asc", model);
    }

    @GetMapping("/newTeacherForm")
    public String showTeacherForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);

        return "new_teacher";
    }
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }
    @GetMapping("/showFormForTeacherUpdate/{id}")
    public String showFormFormTeacherUpdate(@PathVariable(value = "id") long id, Model model) {

        Teacher teacher = teacherService.getTeacherById(id);

        model.addAttribute("teacher", teacher);

        return "update_teacher";
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable(value = "id") long id) {

        this.teacherService.deleteTeacherById(id);

        return "redirect:/teachers";
    }

    @RequestMapping("/pageTeacher/{pageTeacherNo}")
    public String findTeacherPage(@PathVariable (value = "pageTeacherNo") int pageTeacherNo,
                                  @RequestParam("sortField") String sortField,
                                  @RequestParam("sortDir") String sortDir,
                                  Model model) {
        int pageSize = 5;

        Page<Teacher> page = teacherService.selectTeacherPage(pageTeacherNo, pageSize, sortField, sortDir);
        List<Teacher> teachersList = page.getContent();

        model.addAttribute("currentPage", pageTeacherNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField", sortField );
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTeachers", teachersList);

        return "teachers";

    }
}
