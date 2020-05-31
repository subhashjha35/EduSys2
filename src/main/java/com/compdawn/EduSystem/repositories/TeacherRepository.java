package com.compdawn.EduSystem.repositories;

import com.compdawn.EduSystem.entities.Teacher;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Optional<Teacher> findByUsername(String username);
}
