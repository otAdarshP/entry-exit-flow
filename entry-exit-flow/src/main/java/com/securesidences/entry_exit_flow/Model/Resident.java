package com.securesidences.entry_exit_flow.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;


@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;
    @Column(updatable = false)
    @NotBlank
    private String residentName;
    @Column(updatable = false)
    @NotBlank
    private String visitReason;
    @Column(updatable = false)
    @NotBlank
    private String emergencyContact;
    @Column(updatable = false)
    @Future
    @NotNull // what if somebody leaves it empty? check for that.
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnTime;
    @Column(updatable = false)
    @FutureOrPresent
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaveTime;
    @Column(nullable = false)
    private String guardianEmail;
    @Column(nullable = false)
    private String guardianPhoneNo;
    @Column(nullable = false)
    private String gatePassStatus = "Pending";
    @Column(nullable = false)
    private LocalDate approvalDecisionTime;



    public Resident(Long residentId, String residentName, String visitReason, String emergencyContact, LocalDate returnTime, LocalDate leaveTime, String guardianEmail, String guardianPhoneNo, String gatePassStatus, LocalDate approvalDecisionTime){
        this.residentId = residentId;
        this.residentName = residentName;
        this.visitReason = visitReason;
        this.emergencyContact = emergencyContact;
        this.returnTime = returnTime;
        this.leaveTime = leaveTime;
        this.guardianEmail = guardianEmail;
        this.guardianPhoneNo = guardianPhoneNo;
        this.gatePassStatus = gatePassStatus;
        this.approvalDecisionTime = approvalDecisionTime;
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

    public String getGuardianPhoneNo() {
        return guardianPhoneNo;
    }
    public void setGuardianPhoneNo(String guardianPhoneNo) {
        this.guardianPhoneNo = guardianPhoneNo;
    }

    public String getGuardianEmail() {
        return guardianEmail;
    }
    public void setGuardianEmail(String guardianEmail) {
        this.guardianEmail = guardianEmail;
    }

    public String getGatePassStatus() {
        return gatePassStatus;
    }
    public void setGatePassStatus(String gatePassStatus) {
        this.gatePassStatus = gatePassStatus;
    }

    public LocalDate getApprovalDecisionTime() {
        return approvalDecisionTime;
    }
    public void setApprovalDecisionTime(LocalDate approvalDecisionTime) {
        this.approvalDecisionTime = approvalDecisionTime;
    }

    public Resident(){

    }
}
