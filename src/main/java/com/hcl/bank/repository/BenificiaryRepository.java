package com.hcl.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bank.entity.Benificiary;

@Repository
public interface BenificiaryRepository extends JpaRepository<Benificiary, Integer>{

}
