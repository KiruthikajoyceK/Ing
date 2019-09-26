package com.hcl.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bank.entity.Benificiary;

@Repository
public interface BenificiaryRepository extends JpaRepository<Benificiary, Integer>{

	@Query("select b from Benificiary b where b.user.userId = :userId")
	public List<Benificiary> findBenificiaryByUserId(int userId);

}
