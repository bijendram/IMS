package com.ims.categorypolicyservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.categorypolicyservice.ServiceDao.CategoryServiceDao;
import com.ims.categorypolicyservice.entity.Category;
import com.ims.categorypolicyservice.repo.CategoryRepository;
import com.ims.categorypolicyservice.repo.PolicyRepository;

@Service
public class CategorySerivce implements CategoryServiceDao {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private PolicyRepository policyRepo;
	

	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		Category findByCatName = categoryRepo.findByCatName(category.getCatName());
		
		if(findByCatName != null) {
			return category;
		}else
		return categoryRepo.save(category);
	}

	@Override
	public boolean deleteCategory(String catId) {
		// TODO Auto-generated method stub
		Optional<Category> findById = categoryRepo.findById(catId);
		Category category = findById.get();
		
		if(category != null) {
			categoryRepo.deleteById(catId);
			policyRepo.deleteByCatName(category.getCatName());
			return true;
		}
		
		return false;
	}

	@Override
	public Category updateCategory(Category category) {
		Optional<Category> findById = categoryRepo.findById(category.getCatId());
		Category exestingCategory = findById.get();
		if(exestingCategory != null) {
			exestingCategory.setCatName(category.getCatName());
			return categoryRepo.save(exestingCategory);
		}
		
		return null;
	}

	@Override
	public Category getCategoryById(String catId) {
		return categoryRepo.findById(catId).orElse(null);
	}

}
