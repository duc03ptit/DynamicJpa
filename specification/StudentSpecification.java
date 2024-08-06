package com.nducmd.dynamicjpa.specification;

import com.nducmd.dynamicjpa.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    // Tìm kiếm với điều kiện =
    public static Specification<Student> withEqualCriteria(StudentSearchCriteria criteria) {
        return (root, query, builder) -> {
            Specification<Student> spec = Specification.where(null);

            spec = addEqualSpec(spec,"name", criteria.getName());
            spec = addEqualSpec(spec,"studentClass", criteria.getStudentClass());
            spec = addEqualSpec(spec,"dob", criteria.getDob());
            spec = addEqualSpec(spec,"studentCode", criteria.getStudentCode());
            spec = addEqualSpec(spec,"gender", criteria.getGender());
            spec = addEqualSpec(spec,"address", criteria.getAddress());
            spec = addEqualSpec(spec,"email", criteria.getEmail());
            spec = addEqualSpec(spec,"major", criteria.getMajor());
            spec = addEqualSpec(spec,"faculty", criteria.getFaculty());
            spec = addEqualSpec(spec,"studyStatus", criteria.getStudyStatus());

            if (criteria.getMinGpa() != null) {
                spec = spec.and((root1, query1, builder1)
                        -> builder1.greaterThanOrEqualTo(root1.get("gpa"), criteria.getMinGpa()));
            }

            if (criteria.getMaxGpa() != null) {
                spec = spec.and((root1, query1, builder1)
                        -> builder1.lessThanOrEqualTo(root1.get("gpa"), criteria.getMaxGpa()));
            }

            return spec.toPredicate(root, query, builder);
        };
    }

    // Tìm kiếm với điều kiện like
    public static Specification<Student> withLikeCriteria(StudentSearchCriteria criteria) {
        return (root, query, builder) -> {
            Specification<Student> spec = Specification.where(null);

            spec = addLikeSpec(spec,"name", criteria.getName());
            spec = addLikeSpec(spec,"studentClass", criteria.getStudentClass());
            spec = addLikeSpec(spec,"studentCode", criteria.getStudentCode());
            spec = addLikeSpec(spec,"gender", criteria.getGender());
            spec = addLikeSpec(spec,"address", criteria.getAddress());
            spec = addLikeSpec(spec,"email", criteria.getEmail());
            spec = addLikeSpec(spec,"major", criteria.getMajor());
            spec = addLikeSpec(spec,"faculty", criteria.getFaculty());
            spec = addLikeSpec(spec,"studyStatus", criteria.getStudyStatus());

            spec = addEqualSpec(spec,"dob", criteria.getDob());

            if (criteria.getMinGpa() != null) {
                spec = spec.and((root1, query1, builder1)
                        -> builder1.greaterThanOrEqualTo(root1.get("gpa"), criteria.getMinGpa()));
            }

            if (criteria.getMaxGpa() != null) {
                spec = spec.and((root1, query1, builder1)
                        -> builder1.lessThanOrEqualTo(root1.get("gpa"), criteria.getMaxGpa()));
            }

            return spec.toPredicate(root, query, builder);
        };
    }

    private static <T> Specification<Student> addEqualSpec(Specification<Student> spec, String attribute, T value) {
        if (value != null) {
            spec = spec.and((root1, query1, builder1)
                    -> builder1.equal(root1.get(attribute), value));
        }
        return spec;
    }

    private static <T> Specification<Student> addLikeSpec(Specification<Student> spec, String attribute, T value) {
        if (value != null) {
            spec = spec.and((root1, query1, builder1)
                    -> builder1.like(root1.get(attribute), "%" + value + "%"));
        }
        return spec;
    }
}
