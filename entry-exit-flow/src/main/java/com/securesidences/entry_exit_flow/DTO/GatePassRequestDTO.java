package com.securesidences.entry_exit_flow.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
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
    
}
