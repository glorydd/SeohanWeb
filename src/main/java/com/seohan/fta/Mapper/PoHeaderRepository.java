package com.seohan.fta.Mapper;

import com.seohan.fta.Domain.PoHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoHeaderRepository extends JpaRepository<PoHeader, Long> {
//    List<PoHeader> findAll();

    @Query(value = " SELECT a, b FROM PoHeader a " +
                    " inner join a.poDetails b on a.cogb = b.cogb and a.pono =b.pono  " +
                    " where a.cogb = :cogb and  a.pono = :pono")
    PoHeader findByCogbAndPono(@Param("cogb") String cogb, @Param("pono") String pono);


    @Query(value = "select a, b from PoHeader a " +
            " left join com.seohan.fta.Domain.PoDetail b on a.cogb=b.cogb and a.pono=b.pono " +
            " where a.cogb=:cogb and a.poYmd=:poYmd")
    List<PoHeader> findByCogbAndPoYmd(String cogb, String poYmd);
}
