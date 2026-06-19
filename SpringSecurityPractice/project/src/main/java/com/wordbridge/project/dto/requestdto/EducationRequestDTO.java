package com.wordbridge.project.dto.requestdto;

import com.wordbridge.project.enums.EducationLevel;
import com.wordbridge.project.enums.ResultType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EducationRequestDTO {


    private EducationLevel educationLevel;

    private String board;

    private String institution;

    private String fieldOfStudy;


    private ResultType resultType;
    private Double result;
    private Double outOf;
    private String gradeOrDivision;

    private LocalDate startDate;
    private LocalDate endDate;


    private Long userProfileId;


}
