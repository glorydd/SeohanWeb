package com.seohan.erp.base.Repository;

import java.util.List;

import com.seohan.erp.base.Domain.Bom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends JpaRepository<Bom, Long> {
	Page<Bom> findAll(Pageable pageable);

	@Query(value=" select distinct a.* from SMLIB.ITMSTPF_VW A WHERE pumgb=:pumgb ", nativeQuery=true)
	List<Bom> findByPumgb(String pumgb, Pageable pageable);
}