package com.securesidences.entry_exit_flow.DTO;

import java.time.LocalDate;

public class GatePassDecisionDTO {
    private String gatePassStatus;
    private LocalDate approvalDecisionTime;

    public GatePassDecisionDTO(String gatePassStatus, LocalDate approvalDecisionTime) {
        this.gatePassStatus = gatePassStatus;
        this.approvalDecisionTime = approvalDecisionTime;
    }
    public String getGatePassStatus() { return gatePassStatus; }
    public LocalDate getApprovalDecisionTime() { return approvalDecisionTime; }
}
