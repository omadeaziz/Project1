package com.example.api1.services;

import com.example.api1.models.Reimbursement;
import com.example.api1.repos.ReimbursementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ReimbursementServiceTest {



    @Autowired
    ReimbursementService reimbursementService;

    @Autowired
    ReimbursementRepository reimbursementRepository;

    private Reimbursement reimbursement;

    @BeforeEach
    public void initEachTest() {
        reimbursement = Reimbursement.builder().id(1).reimbType("Dentist").amount(99).status("Review").build();
        reimbursementRepository.save(reimbursement);
    }


    @Test
    void saveReimbursement() {
        Reimbursement newReimbursement = Reimbursement.builder().id(2).reimbType("chair").amount(200).status("Denied").build();
        reimbursementRepository.save(newReimbursement);

        Assertions.assertEquals(2, reimbursementRepository.findById(2).get().getId());
        Assertions.assertEquals("chair", reimbursementRepository.findById(2).get().getReimbType());
        Assertions.assertEquals(200, reimbursementRepository.findById(2).get().getAmount());
        Assertions.assertEquals("Denied", reimbursementRepository.findById(2).get().getStatus());
    }

    @Test
    void getAllReimbursements() {
        List<Reimbursement> reimbursementList = reimbursementService.getAllReimbursements();
        Assertions.assertEquals(1,reimbursementList.get(0).getId());
        Assertions.assertEquals("Dentist",reimbursementList.get(0).getReimbType());
        Assertions.assertEquals(99,reimbursementList.get(0).getAmount());
        Assertions.assertEquals("Review",reimbursementList.get(0).getStatus());
    }

}