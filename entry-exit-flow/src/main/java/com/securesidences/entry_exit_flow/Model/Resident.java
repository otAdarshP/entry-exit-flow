package com.securesidences.entry_exit_flow.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class Resident {
    @Id
    private Long residentId;
    private String residentName;


    public Resident(){

    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public Resident(Long residentId, String residentName){
        this.residentId = residentId;
        this.residentName = residentName;
    }


    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }
}
