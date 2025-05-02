package com.securesidences.entry_exit_flow.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    private String residentName;
    private String visitReason;
    private String emergencyContact;
    private LocalDate returnTime;
    private LocalDate leaveTime;



    public Resident(Long residentId, String residentName, String visitReason, String emergencyContact, LocalDate returnTime, LocalDate leaveTime){
        this.residentId = residentId;
        this.residentName = residentName;
        this.visitReason = visitReason;
        this.emergencyContact = emergencyContact;
        this.returnTime = returnTime;
        this.leaveTime = leaveTime;
    }

    public String getResidentName() {
        return residentName;
    }
    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public LocalDate getReturnTime() {
        return returnTime;
    }
    public void setReturnTime(LocalDate returnTime) {
        this.returnTime = returnTime;
    }

    public Long getResidentId() {
        return residentId;
    }
    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getVisitReason() {
        return visitReason;
    }
    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public LocalDate getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveTime(LocalDate leaveTime) {
        this.leaveTime = leaveTime;
    }
}
