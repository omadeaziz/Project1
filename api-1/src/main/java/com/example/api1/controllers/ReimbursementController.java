package com.example.api1.controllers;

import com.example.api1.models.Reimbursement;
import com.example.api1.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReimbursementController {
    // Creating CRUD methods
    // Employee can get all reimbursements'
    // Employee can submit reimbursement (post)

    @Autowired
    ReimbursementService reimbursementService;

    /**
     * Post request to submit a new reimbursement from employee
     * @param reimbursement
     * @return email confirmation that email was sent to manager
     */
    @PostMapping("/employee/new-reimbursement")
    public String createReimbursement(@RequestBody Reimbursement reimbursement) {
        reimbursementService.saveReimbursement(reimbursement);

        return reimbursementService.sendEmail("manager");
    }

    /**
     * Both manager and employee can see all reimbursements
     * @return
     */
    @GetMapping("/employee/view-reimbursements")
    public List<Reimbursement> viewReimbursements() {
        return reimbursementService.getAllReimbursements();
    }

    /**
     * Manager updating a reimbursement by approving and rejecting
     * @param reimbursement
     * @return email confirmation that was sent to employee
     */
    @PutMapping("/manager/update-reimbursement")
    public String updateReimbursement(@RequestBody Reimbursement reimbursement) {
        // first find the reimbursement in the table
        List<Reimbursement> reimbursementList = reimbursementService.getAllReimbursements();
        Reimbursement r = reimbursementList.stream()
                .filter(u -> u.getId() == reimbursement.getId())
                .findFirst().orElse(null);

        r.setStatus(reimbursement.getStatus());
        reimbursementService.saveReimbursement(r);

        return reimbursementService.sendEmail("employee");
    }


}