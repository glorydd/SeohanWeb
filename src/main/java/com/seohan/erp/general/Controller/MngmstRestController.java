package com.seohan.erp.general.Controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seohan.erp.general.Domain.FoodTable;
import com.seohan.erp.general.Domain.FoodTableKamtec;
import com.seohan.erp.general.Domain.FoodTableLab;
import com.seohan.erp.general.Repository.FoodTableKamtecRepository;
import com.seohan.erp.general.Repository.FoodTableLabRepository;
import com.seohan.erp.general.Repository.FoodTableRepository;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/general")
@Slf4j
@RestController
class MngmstRestController {

    @Autowired
    private FoodTableRepository foodTableRepo;
    @Autowired
    private FoodTableKamtecRepository foodTableKamtecRepo;
    @Autowired
    private FoodTableLabRepository foodTableLabRepo;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat formatsdf = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/foodTable")
    public @ResponseBody List<FoodTable> FoodTable(@RequestParam String gdate) throws Exception {
        return foodTableRepo.findByGdate(gdate);
    }

    @GetMapping("/foodTableKamtec")
    public @ResponseBody List<FoodTableKamtec> FoodTableKamtec(@RequestParam String gdate) throws Exception {
        FoodTable foodTable = new FoodTable();
        foodTable.setGdate(gdate);
        return foodTableKamtecRepo.findByGdate(foodTable.getGdate());
    }

    @GetMapping("/foodTableLab")
    public @ResponseBody List<FoodTableLab> FoodTableLab(@RequestParam String gdate) throws Exception {
        FoodTable foodTable = new FoodTable();
        foodTable.setGdate(gdate);
        return foodTableLabRepo.findByGdate(foodTable.getGdate());
    }

}
