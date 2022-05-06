package com.example.api1.repos;

import com.example.api1.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {

}
