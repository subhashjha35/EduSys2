package com.compdawn.EduSystem.repositories;

import com.compdawn.EduSystem.entities.Subject;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author subhash
 */
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    public Optional<Subject> findById(Integer id);
    
}
