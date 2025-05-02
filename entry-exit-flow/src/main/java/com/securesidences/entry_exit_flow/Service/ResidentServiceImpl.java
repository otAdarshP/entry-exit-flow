package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.Model.Resident;
import com.securesidences.entry_exit_flow.Repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService{

    @Autowired
    private ResidentRepository residentRepository;
    private long nextId =1L;

    @Override
    public void createResident(Resident resident) {
        residentRepository.save(resident);
    }

    @Override
    public ArrayList<Resident> allResidents() {
        return (ArrayList<Resident>) residentRepository.findAll();
    }

    @Override
    public String deleteResident(Long residentId) {
        Resident resident = residentRepository.findById(residentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resident ID not found"));

        residentRepository.delete(resident);
        return "Resident with resident ID " + residentId + " deleted successfully!";
    }


    @Override
    public Resident updateResident(Resident updateResident, Long residentId) {
        Resident existing = residentRepository.findById(residentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found."));
        existing.setResidentName(updateResident.getResidentName());
        return residentRepository.save(existing);
    }
}
