package com.seohan.erp.mat.Repository;

import com.seohan.erp.mat.Domain.ImportPlan;
import com.seohan.erp.mat.Domain.ItemBalance;
import com.seohan.erp.mat.Dto.ImportPlanAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportPlanRepository extends JpaRepository<ImportPlan, Long> {
	@Override
	List<ImportPlan> findAll();

	ItemBalance findByWarhsAndItmno(String warhs, String itmno);

	@Query(value = " SELECT DISTINCT JDATE FROM SMLIB.PUR_PLNA WHERE STAT ='S' AND SABUN = :empid " , nativeQuery=true)
	String findMaxWorkDate(@Param("empid") String empid);

}
