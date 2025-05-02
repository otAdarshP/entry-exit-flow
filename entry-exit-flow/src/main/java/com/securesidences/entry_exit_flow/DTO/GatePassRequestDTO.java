package com.securesidences.entry_exit_flow.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class GatePassRequestDTO {
    @NotBlank
    private String visitReason;
    @NotBlank
    private String emergencyContact;
    @FutureOrPresent
    @NotNull
    private LocalDate leaveTime;
    @Future
    @NotNull
    private LocalDate returnTime;

    public GatePassRequestDTO(String visitReason, String emergencyContact, LocalDate leaveTime, LocalDate returnTime) {
        this.visitReason = visitReason;
        this.emergencyContact = emergencyContact;
        this.leaveTime = leaveTime;
        this.returnTime = returnTime;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDate leaveTime) {
        this.leaveTime = leaveTime;
    }

    public LocalDate getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDate returnTime) {
        this.returnTime = returnTime;
    }
}
