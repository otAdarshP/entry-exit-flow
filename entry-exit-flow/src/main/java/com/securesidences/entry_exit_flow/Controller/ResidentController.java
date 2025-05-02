package com.securesidences.entry_exit_flow.Controller;


import com.securesidences.entry_exit_flow.DTO.GatePassRequestDTO;
import com.securesidences.entry_exit_flow.Model.Resident;
import com.securesidences.entry_exit_flow.Service.ResidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping("/api/public/residents")
    public ResponseEntity<List<Resident>> allResidents(){
        List<Resident> residents = residentService.allResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @PostMapping("/api/public/residents")
    public ResponseEntity<String> createResident (@RequestBody List<Resident> residents){
        residents.forEach(residentService::createResident);
        return new ResponseEntity<>("Resident added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/resident/{residentId}")
    public ResponseEntity<String> deleteResident(@PathVariable Long residentId){
        try {
            String status = residentService.deleteResident(residentId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        catch (ResponseStatusException exception){
            return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
        }
    }

    @PutMapping("/api/public/resident/{residentId}")
    public ResponseEntity<String> updateResident (@RequestBody Resident resident, @PathVariable Long residentId){
        try {
            Resident savedResident = residentService.updateResident(resident, residentId);
            return new ResponseEntity<>("Resident with resident id " + residentId + " updated successfully!", HttpStatus.OK);
        }
        catch (ResponseStatusException exception){
            return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
        }
    }


//    gatepass APIs

//    @PostMapping("/api/resident/gatepass")
//    public ResponseEntity<String> generateGatepPass (@RequestBody @Valid GatePassRequestDTO dto){ // add @Valid when DTO are fixed
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Resident saved = residentService.createGatePass(dto, username);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Gate pass has been generated and sent for approval.");
//    }

    // TEMP: Accept email directly from request instead of relying on login
    @PostMapping("/api/resident/gatepass")
    public ResponseEntity<String> generateGatePass(
            @RequestParam("residentEmail") String residentEmail,
            @RequestBody @Valid GatePassRequestDTO dto) {

        Resident saved = residentService.createGatePass(dto, residentEmail);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Gate pass has been generated and sent for approval.");
    }


    @PutMapping("/api/approve/{residentId}")
    public ResponseEntity<String> approveGatePass (@PathVariable Long residentId, @RequestParam("decision") String decision){
        residentService.updateGatePassStatus(residentId, decision);
        return ResponseEntity.ok("Gate pass has been: " + decision);
    }

}
