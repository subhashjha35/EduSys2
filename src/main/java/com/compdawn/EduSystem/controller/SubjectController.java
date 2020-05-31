package com.compdawn.EduSystem.controller;

import com.compdawn.EduSystem.entities.Subject;
import com.compdawn.EduSystem.entities.Teacher;
import com.compdawn.EduSystem.repositories.SubjectRepository;
import com.compdawn.EduSystem.repositories.TeacherRepository;
import com.compdawn.EduSystem.security.AuthenticatedUser;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author subhash
 */
@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    private Teacher getTeacher(Principal principal) {
        String username = principal.getName();
        return teacherRepository.findByUsername(username).get();
    }

    @GetMapping("")
    public String list(Model model, Principal principal) {
        Teacher teacher = getTeacher(principal);
        Iterable<Subject> subjects = teacher.getSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("test", "TestName");
        return "subject-list";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) throws Exception {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (oSubject.isPresent()) {
            Subject subject = oSubject.get();
            model.addAttribute("subject", subject);
//            model.addAttribute("message", new Message());
            return "subject";
        }
        throw new Exception("No such subject id");
    }
    
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("subject", new Subject());
//        model.addAttribute("allLabels", labelRepository.findAll());
//        model.addAttribute("issueLabels", new ArrayList<Integer>());

        return "subject-form";
    }
    
    @PostMapping("/new")
    public String addSubject(@Valid Subject subject, BindingResult bindingResult, Model model, Principal principal, ModelMap modelMap, @RequestParam(value="publish", required=false) boolean publish) {
        if (bindingResult.hasErrors()) {
            modelMap.put(BindingResult.class.getName() + ".subject", bindingResult);
            return "subject-form";
        }
            
        String username = principal.getName();
        Teacher teacher = teacherRepository.findByUsername(username).get();
        subject.setTeacher(teacher);
        subject.setPublic_flag((publish)?publish:Boolean.FALSE);
        
        subjectRepository.save(subject);
        return "redirect:/subjects";

    }
    
    @GetMapping("/edit/{id}/publish/{publish}")
    public String editIssue(@Valid Subject dbSubject, BindingResult bindingResult, Model model, Principal principal,
    @PathVariable("id") Integer id, @PathVariable("publish") boolean publish_val) {

        dbSubject = subjectRepository.findById(id).get();
        dbSubject.setPublic_flag(publish_val);

        subjectRepository.save(dbSubject);
        return "redirect:/subjects";
    }
}