package com.securesidences.entry_exit_flow.DTO;

import java.time.LocalDate;

public class GatePassOverviewDTO {
    private Long residentId;
    private String residentName;
    private LocalDate leaveTime;
    private LocalDate returnTime;
    private String gatePassStatus;
    private String guardianEmail;

    public GatePassOverviewDTO(Long residentId, String residentName,
                               LocalDate leaveTime, LocalDate returnTime,
                               String gatePassStatus, String guardianEmail) {
        this.residentId = residentId;
        this.residentName = residentName;
        this.leaveTime = leaveTime;
        this.returnTime = returnTime;
        this.gatePassStatus = gatePassStatus;
        this.guardianEmail = guardianEmail;
    }

    // getters only
    public Long getResidentId() { return residentId; }
    public String getResidentName() { return residentName; }
    public LocalDate getLeaveTime() { return leaveTime; }
    public LocalDate getReturnTime() { return returnTime; }
    public String getGatePassStatus() { return gatePassStatus; }
    public String getGuardianEmail() { return guardianEmail; }
}
