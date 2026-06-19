package com.wordbridge.project.dto.requestdto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PortfolioRequestDTO {

    private String title;
    private String description;

    private String projectUrl;

    private String technologies;

    private Long userProfileId;


}
