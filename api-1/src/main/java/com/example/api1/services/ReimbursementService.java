package com.example.api1.services;


import com.example.api1.models.Reimbursement;
import com.example.api1.repos.ReimbursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReimbursementService {

    final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);


    private ReimbursementRepository reimbursementRepository;



    @Autowired
    RestTemplate restTemplate;

    public ReimbursementService(ReimbursementRepository reimbursementRepository) {
        this.reimbursementRepository = reimbursementRepository;
    }

    /**
     * Saves a reimbursement object in the database
     * @param reimbursement
     */
    public void saveReimbursement(Reimbursement reimbursement) {
        reimbursementRepository.save(reimbursement);
        logger.info("New reimbursements saved in database");
    }

    /**
     * @return array list of reimbursements
     */
    public List<Reimbursement> getAllReimbursements() {
        logger.info("Finding all reimbursements from database");
        return reimbursementRepository.findAll();
    }


    /**
     * sends an email to the employee or managers
     * @param sendTo String
     * @return confirmation that email was sent
     */
    public String sendEmail(String sendTo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        logger.info("Requesting email to be sent");

        return restTemplate.exchange("http://api2:8080/send-email-"+sendTo, HttpMethod.GET, entity, String.class).getBody();

    }
}
