package com.nducmd.dynamicjpa.specification;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentSearchCriteria {
    private String name;
    private String studentClass;
    private LocalDate dob;
    private String studentCode;
    private String gender;
    private String address;
    private String email;
    private String major;
    private String faculty;
    private String studyStatus;
    private Double minGpa;
    private Double maxGpa;
}
