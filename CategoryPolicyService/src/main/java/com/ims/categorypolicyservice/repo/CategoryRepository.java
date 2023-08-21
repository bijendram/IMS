package com.ims.categorypolicyservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ims.categorypolicyservice.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

	Category findByCatName(String catName);
	

}
