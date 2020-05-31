package com.compdawn.EduSystem.dtos;

import com.compdawn.EduSystem.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubjectListDTO extends Subject {
    private long count;
    
    public SubjectListDTO(Integer id, String name, String code, Integer credit, Boolean public_flag, long count) {
        super(id, name, code, credit, public_flag,null);
    }
}
