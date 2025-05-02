package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.Model.Resident;

import java.util.ArrayList;
import java.util.List;

public interface ResidentService {

    void createResident(Resident resident);
    List<Resident> allResidents();
    String deleteResident (Long residentId);
    Resident updateResident (Resident updateResident, Long residentId);


}
