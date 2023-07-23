package com.medicare.medicare_v1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.medicare_v1.Entity.Purchase;

@Repository
public interface IPurchaseRepo extends JpaRepository<Purchase, Integer>{

}
