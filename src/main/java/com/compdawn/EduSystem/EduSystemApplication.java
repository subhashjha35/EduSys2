package com.compdawn.EduSystem;

import com.compdawn.EduSystem.entities.Teacher;
import com.compdawn.EduSystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class EduSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(EduSystemApplication.class, args);
	}

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        for(Teacher teacher : teacherRepository.findAll()) {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacherRepository.save(teacher);
        }
    }
}
