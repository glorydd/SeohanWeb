package com.seohan.general.Mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seohan.general.Domain.FoodTable; 

@Repository
public interface FoodTableRepository extends JpaRepository<FoodTable, Long> {

	List<FoodTable> findByGdate(String gdate); 
}
