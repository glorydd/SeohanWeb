package com.seohan;
import com.seohan.fta.Domain.PoHeader;
import com.seohan.fta.Mapper.PoHeaderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FTATest{

    @Autowired
    private PoHeaderRepository poRepo;

    @Test
    public void test() throws Exception{
        PoHeader poHeader = new PoHeader();
        try {
            poHeader = poRepo.findByCogbAndPono("SEOHAN", "PO20200608043");
            System.out.print(poHeader);
        }catch (Exception e){
            e.printStackTrace();
        };
    }
}