package com.securesidences.entry_exit_flow.Controller;


import com.securesidences.entry_exit_flow.Model.Resident;
import com.securesidences.entry_exit_flow.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/public/resident/{residentId}")
    public ResponseEntity<String> updateResident (@RequestBody Resident resident, @PathVariable Long residentId){
        try {
            Resident savedResident = residentService.updateResident(resident, residentId);
            return new ResponseEntity<>("Resident with resident id " + residentId + " updated successfully!", HttpStatus.OK);
        }
        catch (ResponseStatusException exception){
            return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
        }
    }

}
