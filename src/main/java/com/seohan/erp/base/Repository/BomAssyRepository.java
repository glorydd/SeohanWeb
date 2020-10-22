package com.seohan.erp.base.Repository;

import com.seohan.erp.base.Domain.BomAssy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomAssyRepository extends JpaRepository<BomAssy, Long> {
	Page<BomAssy> findAll(Pageable pageable);

//	@Query(value=" select distinct a.* from SMLIB.ITMSTPF_VW A WHERE pumgb=:pumgb ", nativeQuery=true)
//	List<BomAssy> findByPumgb(String pumgb, Pageable pageable);
}