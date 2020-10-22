package com.seohan.erp.mat.Repository;

import com.seohan.erp.mat.Domain.PurPlna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurPlnARepository extends JpaRepository<PurPlna, Long> {
	@Override
	List<PurPlna> findAll();

//	@Query(value = " SELECT DISTINCT JDATE FROM SMLIB.PUR_PLNA WHERE STAT ='S' AND SABUN = :empid ", nativeQuery = true)

	PurPlna findPurPlnaByStatAndSabun(String stat, String sabun);

}
