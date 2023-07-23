package com.medicare.medicare_v1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.medicare_v1.Entity.Category;
import com.medicare.medicare_v1.Repository.ICategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private ICategoryRepo catRepo;
	
	// Show All Category
	public List<Category> displayAllCategory() {
		return catRepo.findAll();
	}

	public Category addingCat(Category categoryName) {
		return catRepo.save(categoryName);
	}
	
	public Optional<Category> findCatId(Integer category_id) {
		return catRepo.findById(category_id);
	}



	public String getCategoryNameById(int id) {
		 Category category = catRepo.findById(id).orElse(null);
	        if (category != null) {
	            return category.getCategoryName();
	        }
	        return null;
	}

	// Fetch selected category by ID
	public Category getCategoryById(int id) {
		Optional<Category> c = catRepo.findById(id);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}
	
	
		

	

}
