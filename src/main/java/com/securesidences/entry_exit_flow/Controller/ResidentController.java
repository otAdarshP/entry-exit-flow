package com.securesidences.entry_exit_flow.Controller;


import com.securesidences.entry_exit_flow.DTO.GatePassDecisionDTO;
import com.securesidences.entry_exit_flow.DTO.GatePassOverviewDTO;
import com.securesidences.entry_exit_flow.DTO.GatePassRequestDTO;
import com.securesidences.entry_exit_flow.Model.Resident;
import com.securesidences.entry_exit_flow.Service.ResidentService;
import com.securesidences.entry_exit_flow.Service.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*") // specify "http://localhost:19006" for React Native
@RestController
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping("/api/public/resident/{residentId}")
    public ResponseEntity<Resident> getResident(@PathVariable Long residentId) {
        Resident resident = residentService
                .allResidents()
                .stream()
                .filter(r -> r.getResidentId().equals(residentId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resident not found"));
        return ResponseEntity.ok(resident);
    }

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

    @DeleteMapping("/api/admin/resident/{residentId}")
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

    @GetMapping("/api/approve/{residentId}")
    public ResponseEntity<GatePassDecisionDTO> approveViaEmail(
            @PathVariable Long residentId,
            @RequestParam("decision") String decision) {

        // 1) update the status and get the saved entity
        Resident updated = residentService.updateGatePassStatus(residentId, decision);

        // 3) build and return only the two fields
        GatePassDecisionDTO dto = new GatePassDecisionDTO(
                updated.getGatePassStatus(),
                updated.getApprovalDecisionTime()
        );
        return ResponseEntity.ok(dto);
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

    /**
     * Resident view: their current gate-pass status
     */
    @GetMapping("/api/resident/history")
    public ResponseEntity<GatePassOverviewDTO> getMyGatePass(
            @RequestParam("email") String residentEmail) {
        Resident r = residentService.findByEmail(residentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
        GatePassOverviewDTO dto = new GatePassOverviewDTO(
                r.getResidentId(),
                r.getResidentName(),
                r.getLeaveTime(),
                r.getReturnTime(),
                r.getGatePassStatus(),
                r.getGuardianEmail());
        return ResponseEntity.ok(dto);
    }

    /**
     * Warden view: all gate-pass requests
     */
    @GetMapping("/api/admin/gatepasses")
    public ResponseEntity<List<GatePassOverviewDTO>> getAllGatePasses() {
        List<GatePassOverviewDTO> list = residentService.allResidents().stream()
                .map(r -> new GatePassOverviewDTO(
                        r.getResidentId(),
                        r.getResidentName(),
                        r.getLeaveTime(),
                        r.getReturnTime(),
                        r.getGatePassStatus(),
                        r.getGuardianEmail()))
                .toList();
        return ResponseEntity.ok(list);
    }



}
