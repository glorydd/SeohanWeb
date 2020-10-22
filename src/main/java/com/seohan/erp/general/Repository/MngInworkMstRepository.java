package com.seohan.erp.general.Repository;

import com.seohan.erp.general.Domain.MngInworkmstEntity;
import com.seohan.erp.general.Domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MngInworkMstRepository {

    @Autowired
    EntityManager em;

    public List<MngInworkmstEntity> findByRdate(String rdate) {
        return em.createQuery(
                "select m from MngInworkmstEntity m" +
                        " join fetch m.mngInworksubEntities s where m.rdate=:rdate " , MngInworkmstEntity.class)
                .setParameter("rdate", rdate)
                .getResultList();
    }

//    @Override
//    Page<MngInworkmstEntity> findAll(Pageable pageable){
//
//    };

//
//    @Query("select m from MngInworkmstEntity m " +
//            " join fetch m.mngInworksubEntities s where m.rdate='20200923'")
//    List<MngInworkmstEntity> findByJPQL(Pageable pageable);
}
