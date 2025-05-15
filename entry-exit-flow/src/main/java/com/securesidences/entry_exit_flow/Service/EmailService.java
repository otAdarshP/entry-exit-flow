package com.securesidences.entry_exit_flow.Service;

import com.securesidences.entry_exit_flow.Model.Resident;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendApprovalMail(Resident resident) {
        String subject = "Gate pass request - Action required";

        String approveUrl = "http://localhost:8080/api/approve/" + resident.getResidentId() + "?decision=Approved";
        String rejectUrl = "http://localhost:8080/api/approve/" + resident.getResidentId() + "?decision=Not%20Approved";

        String body = "<p>Dear Guardian,</p>"
                + "<p>Please approve the gate pass for <b>" + resident.getResidentName() + "</b>.</p>"
                + "<p>"
                + "<a href='" + approveUrl + "' style='"
                + "background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Approve</a> "
                + "<a href='" + rejectUrl + "' style='"
                + "background-color: #f44336; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Reject</a>"
                + "</p>"
                + "<p>Thank you,<br/>Secure Residences Team</p>";


            try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(resident.getGuardianEmail());
            helper.setSubject(subject);
            helper.setText(body, true);  // true = send as HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // you can improve error handling here
        }
    }

    public void sendDecisionMail(Resident resident) {
        String subject = "Your gate pass has been " + resident.getGatePassStatus();
        String body = "<p>Hi " + resident.getResidentName() + ",</p>"
                + "<p>Your gate pass for <b>"
                + resident.getLeaveTime() + " to "
                + resident.getReturnTime() + "</b> "
                + "has been <b>" + resident.getGatePassStatus() + "</b> by your guardian.</p>"
                + "<p>Decision time: "
                + resident.getApprovalDecisionTime() + "</p>"
                + "<p>Thank you,<br/>Secure Residences Team</p>";

        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(resident.getResidentEmail());
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


//package com.securesidences.entry_exit_flow.Service;
//
//import com.securesidences.entry_exit_flow.Model.Resident;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendApprovalMail (Resident resident){
//        String subject = "Gate pass request - regarding.";
//        String body = "Dear Guardian,\n\nPlease approve the gate pass for " + resident.getResidentName()
//                + ".\n\nClick to approve: http://localhost:8080/api/resident/approve/"
//                + resident.getResidentId() + "?decision=Approved\n"
//                + "Click to reject: http://localhost:8080/api/resident/approve/"
//                + resident.getResidentId() + "?decision=Not%20Approved";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(resident.getGuardianEmail());
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//    }
//
//}
