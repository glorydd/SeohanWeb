package com.seohan.erp.mat;

import com.seohan.erp.mat.Domain.ImportPlan;
import com.seohan.erp.mat.Domain.ItmblpfsubEntity;
import com.seohan.erp.mat.Domain.LocaAlmEntity;
import com.seohan.erp.mat.Dto.ImportPlanAlarm;
import com.seohan.erp.mat.Dto.ItmblpfsubDto;
import com.seohan.erp.mat.Repository.ItmblpfsubRepository;
import com.seohan.erp.mat.Repository.LocaAlmEntityRepository;
import com.seohan.erp.mat.Service.ImportPlanService;
import com.seohan.erp.mat.Service.ItmblpfsubService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.seohan.SeohanWebApplication.dateFormatString;
import static com.seohan.SeohanWebApplication.timeFormatString;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ImportPlanTest {

    @Autowired
    ImportPlanService importPlanService;

    @Test
    public void 자재결품조회 (){
        List<ImportPlanAlarm> importPlanAlarms = importPlanService.getOmissionItemList("4160038");
        Assert.assertNotNull(importPlanAlarms);
    }
}