package com.emranhss.SAAS.service;

import com.emranhss.SAAS.entity.State;
import com.emranhss.SAAS.repository.StateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepo;

    public StateService(StateRepository stateRepo) {
        this.stateRepo = stateRepo;
    }

    public List<State> getStatesByCountryId(Long countryId) {
        return stateRepo.findByCountryId(countryId);
    }

    public State getStateById(Long id) {
        return stateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found"));
    }

}
