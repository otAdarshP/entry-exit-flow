package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.DTO.GatePassRequestDTO;
import com.securesidences.entry_exit_flow.Model.Resident;
import com.securesidences.entry_exit_flow.Repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService{

    private final EmailService emailService;

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

    public ResidentServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public Resident createGatePass(GatePassRequestDTO dto, String username) {
        Resident resident = residentRepository.findByResidentName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Resident not found."));

//        mapping updates
        resident.setVisitReason(dto.getVisitReason());
        resident.setGatePassStatus("Pending");
        resident.setLeaveTime(dto.getLeaveTime());
        resident.setReturnTime(dto.getReturnTime());
        resident.setEmergencyContact(dto.getEmergencyContact());

        residentRepository.save(resident);

        emailService.sendApprovalMail(resident);
        return resident;
    }

    @Override
    public Resident updateGatePassStatus(Long residentId, String decision) {
        Resident resident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found with resident id: " + residentId));

        if (!decision.equalsIgnoreCase("Approved") && !decision.equalsIgnoreCase("Not approved")){
            throw new IllegalArgumentException("Decision not accepted by the system, please choose from the given two.");
        }

        resident.setGatePassStatus(decision);
        resident.setApprovalDecisionTime(LocalDate.from(LocalDateTime.now()));

        return residentRepository.save(resident);

    }
}
