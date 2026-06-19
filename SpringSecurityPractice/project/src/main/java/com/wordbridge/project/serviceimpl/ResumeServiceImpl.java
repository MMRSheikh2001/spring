package com.wordbridge.project.serviceimpl;

import com.wordbridge.project.dto.responsedto.ResumeResponseDTO;
import com.wordbridge.project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final UserProfileService userProfileService;

    private final EducationService educationService;

    private final ExperienceService experienceService;

    private final UserSkillService userSkillService;

    private final UserLanguageService userLanguageService;

    private final TrainingService trainingService;

    private final PortfolioService portfolioService;

    private final ReferenceService referenceService;

    private final ExtracurricularService extracurricularService;


    @Override
    public ResumeResponseDTO generateResume(Long userProfileId) {
        ResumeResponseDTO dto = new ResumeResponseDTO();

        dto.setProfile(userProfileService.findById(userProfileId));

        dto.setEducations(educationService.findByUserProfileId(userProfileId));

        dto.setExperiences(experienceService.findByUserProfileId(userProfileId));
        dto.setSkills(userSkillService.findByUserProfileId(userProfileId));
        dto.setTrainings(trainingService.findByUserProfileId(userProfileId));
        dto.setPortfolios(portfolioService.findByUserProfileId(userProfileId));
        dto.setLanguages(userLanguageService.findByUserProfileId(userProfileId));
        dto.setReferences(referenceService.findByUserProfileId(userProfileId));
        dto.setExtracurriculars(extracurricularService.findByUserProfileId(userProfileId));
        return dto;
    }

    @Override
    public byte[] generatePdf(Long userProfileId) {
        return new byte[0];
    }
}
