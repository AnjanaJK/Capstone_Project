package com.medicare.medicare_v1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.medicare_v1.Repository.IPurchaseDetails;



@Service
public class PdService {

	@Autowired
	private IPurchaseDetails pdRepo;

	public List<Object[]> showPurchaseReport(int userId) {
		return pdRepo.displayAllReport(userId);
	}
}
