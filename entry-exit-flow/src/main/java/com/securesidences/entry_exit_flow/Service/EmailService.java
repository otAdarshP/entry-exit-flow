package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.Model.Resident;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendApprovalMail (Resident resident){
        String subject = "Gate pass request - regarding.";
        String body = "Dear Guardian,\n\nPlease approve the gate pass for " + resident.getResidentName()
                + ".\n\nClick to approve: http://localhost:8080/api/resident/approve/"
                + resident.getResidentId() + "?decision=Approved\n"
                + "Click to reject: http://localhost:8080/api/resident/approve/"
                + resident.getResidentId() + "?decision=Not%20Approved";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(resident.getGuardianEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
