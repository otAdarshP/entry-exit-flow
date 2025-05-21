package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.DTO.GatePassRequestDTO;
import com.securesidences.entry_exit_flow.Model.Resident;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ResidentService {

    void createResident(Resident resident);
    List<Resident> allResidents();
    String deleteResident (Long residentId);
    Resident updateResident (Resident updateResident, Long residentId);

    Resident createGatePass (GatePassRequestDTO dto, String username); // the unique information given during sign-in to the Spring Security.
    Resident updateGatePassStatus (Long residentId, String decision);
    Optional<Resident> findByEmail(String email);
}
