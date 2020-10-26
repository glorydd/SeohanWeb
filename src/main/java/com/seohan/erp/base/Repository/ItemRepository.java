package com.seohan.erp.base.Repository;

import com.seohan.erp.base.Domain.ItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemView, Long> {
	Page<ItemView> findAll(Pageable pageable);

	ItemView findByItmno(String itmno);

	@Query(value="select distinct a.* from SMLIB.ITMSTPF_VW A WHERE ACTGB=:actgb", nativeQuery=true)
	Page<ItemView> findByActgb(String actgb, Pageable pageable);

	Page<ItemView> findByItmnoContains(String itmno, Pageable pageable);

	Page<ItemView> findByItmnoContainsOrPumgbContains(String itmno, String pumgb, Pageable pageable);


	@Query(value="select distinct a.* from SMLIB.ITMSTPF_VW A WHERE pumgb=:pumgb", nativeQuery=true)
	List<ItemView> findByPumgb(String pumgb, Pageable pageable);
}
