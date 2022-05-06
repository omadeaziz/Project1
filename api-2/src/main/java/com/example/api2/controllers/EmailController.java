package com.example.api2.controllers;

import com.example.api2.Email;
import com.example.api2.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private Email email;

    @RequestMapping("/send-email-{sendTo}")
    public String send(@PathVariable String sendTo) {
        if (sendTo.equals("manager")) {
            email.setEmailAddress("manager.project1.revature@gmail.com");
            email.setSubject("New Reimbursement Submitted");
            email.setBody("Please review new reimbursement and update status");
        } else {
            email.setEmailAddress("employee.project1.revature@gmail.com");
            email.setSubject("Reimbursement updated");
            email.setBody("Please see status of submitted reimbursement");
        }

        try {
            mailService.sendEmail(email);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Email was sent to " + sendTo;
    }
}
