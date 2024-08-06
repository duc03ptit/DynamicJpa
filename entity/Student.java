package com.nducmd.dynamicjpa.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String studentClass;
    LocalDate dob;
    String studentCode;
    String gender;
    String address;
    String email;
    String major;
    String faculty;
    String studyStatus;
    Double gpa;

}
