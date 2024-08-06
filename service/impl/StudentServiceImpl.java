package com.nducmd.dynamicjpa.service.impl;

import com.nducmd.dynamicjpa.entity.Student;
import com.nducmd.dynamicjpa.repository.StudentRepository;
import com.nducmd.dynamicjpa.service.StudentService;
import com.nducmd.dynamicjpa.specification.StudentSearchCriteria;
import com.nducmd.dynamicjpa.specification.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Page<Student> getStudents(StudentSearchCriteria criteria,
                                     int page,
                                     int size,
                                     String sortDirection,
                                     String sortBy,
                                     int mode) {

        Specification<Student> spec = null;
        if (mode == 0) {
            spec = StudentSpecification.withEqualCriteria(criteria);
        } else if (mode == 1) {
            spec = StudentSpecification.withLikeCriteria(criteria);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(spec, pageable);
    }
}
