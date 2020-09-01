package com.seohan.scheduler;

import com.seohan.erp.mat.Domain.ItemBalance;
import com.seohan.erp.mat.Domain.ItemBalanceHeader;
import com.seohan.erp.mat.Domain.ItemBalanceHis;
import com.seohan.erp.mat.Domain.ItemBalanceHisOld;
import com.seohan.erp.mat.Dto.ItemBalanceSaveQuery;
import com.seohan.erp.mat.Mapper.ItemBalanceHeaderMapper;
import com.seohan.erp.mat.Mapper.ItemBalanceHisMapper;
import com.seohan.erp.mat.Mapper.ItemBalanceHisOldMapper;
import com.seohan.erp.mat.Repository.ItemBalanceHeaderRepository;
import com.seohan.erp.mat.Repository.ItemBalanceHisOldRepository;
import com.seohan.erp.mat.Repository.ItemBalanceHisRepository;
import com.seohan.erp.mat.Service.ItemBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class ScheduledJobs {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ItemBalanceService itemBalanceService;

    @Autowired
    private ItemBalanceHisRepository itemBalanceHisRepository;

    @Autowired
    private ItemBalanceHisMapper itemBalanceHisMapper;

    @Autowired
    private ItemBalanceHisOldRepository itemBalanceHisOldRepository;

    @Autowired
    private ItemBalanceHisOldMapper itemBalanceHisOldMapper;

    @Autowired
    private ItemBalanceHeaderRepository itemBalanceHeaderRepository;

    @Autowired
    private ItemBalanceHeaderMapper itemBalanceHeaderMapper;

    @Transactional
    public Boolean saveBalance(String savingDateString, String savingTimeString) {

        LocalDate savingDateTime = LocalDate.parse(savingDateString, DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate oldDateTime = savingDateTime.plusDays(150);
        String oldDate = oldDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        ItemBalanceSaveQuery itemBalanceSaveQuery = ItemBalanceSaveQuery.builder()
                .savingDate(savingDateString)
                .savingTime(savingTimeString)
                .oldDate(oldDate)
                .build();

        try {
            itemBalanceService.saveBalance();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//    @Transactional
//    public void saveBalanceOldByDate(String savingDateString, String savingTimeString) {
//        String targetTable = "";
//
//        LocalDate saveDate = LocalDate.parse(savingDateString, DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate oldDateTime = saveDate.minusDays(150);
//        String oldDate = oldDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//
//        ItemBalanceSaveQuery itemBalanceSaveQuery = ItemBalanceSaveQuery.builder()
//                .savingDate(savingDateString)
//                .savingTime(savingTimeString)
//                .oldDate(oldDate)
//                .build();
//        itemBalanceHisOldMapper.saveOldBalanceByDate(itemBalanceSaveQuery);
//    }



}