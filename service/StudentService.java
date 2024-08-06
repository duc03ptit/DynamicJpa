package com.nducmd.dynamicjpa.service;

import com.nducmd.dynamicjpa.entity.Student;
import com.nducmd.dynamicjpa.specification.StudentSearchCriteria;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<Student> getStudents(StudentSearchCriteria criteria,
                                     int page,
                                     int size,
                                     String sortDirection,
                                     String sortBy,
                                     int mode);
}
