package com.seohan.erp.base.Repository;

import java.util.List;

import com.seohan.erp.base.Domain.LineView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<LineView, Long> {
	
	List<LineView> findByActgb(String actgb);
	
	List<LineView> findByFact(String fact);
	
	LineView findByWrkct(String wrkct);  
}
