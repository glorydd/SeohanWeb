package com.seohan.erp.base.Repository;

import java.util.List;

import com.seohan.erp.base.Domain.Code;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> { 
	@Query(value="select distinct a.* from SALIB.ACODERP A WHERE ADGUB NOT IN ('Q2','OD','MR','MP','22','GD','PY','00','M5','PB')", nativeQuery=true)
	Page<Code> findAll(Pageable pageable);

	Page<Code> findByAdgub(String adgub, Pageable pageable);

	List<Code> findByAdgubAndAref1AndAfill( String adgub, String aref1, String afill);
	
	@Query(value="select distinct a.* from SALIB.ACODERP A WHERE ADGUB='31' and aref1='SHN' order by afill", nativeQuery=true)
	public List<Code> findFact() throws Exception;
}
