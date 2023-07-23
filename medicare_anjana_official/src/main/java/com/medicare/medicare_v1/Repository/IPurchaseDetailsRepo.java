package com.medicare.medicare_v1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.medicare_v1.Entity.PurchaseDetails;

@Repository
public interface IPurchaseDetailsRepo extends JpaRepository<PurchaseDetails, Integer>{

}
