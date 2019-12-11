package com.seohan.general.Controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seohan.general.Domain.CodeLib;
import com.seohan.general.Domain.FoodTable;
import com.seohan.general.Domain.FoodTableKamtec;
import com.seohan.general.Domain.FoodTableLab;
import com.seohan.general.Domain.TemperatureData;
import com.seohan.general.Mapper.CodeLibRepository;
import com.seohan.general.Mapper.FoodTableKamtecRepository;
import com.seohan.general.Mapper.FoodTableLabRepository;
import com.seohan.general.Mapper.FoodTableRepository;
import com.seohan.general.Mapper.TemperatureRepository;

import lombok.extern.slf4j.Slf4j; 


@RequestMapping("/general")
@Slf4j 
@RestController
class ETCRestController { 

	@Autowired
	private CodeLibRepository codeLibRepo;
	@Autowired
	private FoodTableRepository foodTableRepo;
	@Autowired
	private FoodTableKamtecRepository foodTableKamtecRepo;	
	@Autowired
	private FoodTableLabRepository foodTableLabRepo;	
	@Autowired
	private TemperatureRepository temperatureRepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatsdf = new SimpleDateFormat("yyyy-MM-dd");
	 
	@GetMapping( "/foodTable")
	public @ResponseBody List<FoodTable> FoodTable(@RequestParam String gdate) throws Exception {		
		FoodTable foodTable= new FoodTable();
		foodTable.setGdate(gdate);
		return foodTableRepo.findByGdate(foodTable.getGdate());  
	}
	@GetMapping( "/foodTableKamtec")
	public @ResponseBody List<FoodTableKamtec> FoodTableKamtec(@RequestParam String gdate) throws Exception {		
		FoodTable foodTable= new FoodTable();
		foodTable.setGdate(gdate);
		return foodTableKamtecRepo.findByGdate(foodTable.getGdate());  
	}
	@GetMapping( "/foodTableLab")
	public @ResponseBody List<FoodTableLab> FoodTableLab(@RequestParam String gdate) throws Exception {		
		FoodTable foodTable= new FoodTable();
		foodTable.setGdate(gdate);
		return foodTableLabRepo.findByGdate(foodTable.getGdate());  
	}

	@GetMapping( "/temperature")
	public @ResponseBody List<TemperatureData> TemperatureList(TemperatureData temperatureData) throws Exception {		
		return temperatureRepo.TemperatureDataList(temperatureData.getCO_GB(), temperatureData.getCO_GB());				
	}
	 
}
