package com.seohan.general.Controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.seohan.general.Domain.Dto;
import com.seohan.general.Domain.Food;
import com.seohan.general.Domain.ItDamage;
import com.seohan.general.Service.EtcService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j; 


@RequestMapping("/general")
@RestController
@Slf4j 
class EtcRestController { 
	@Autowired
	private EtcService etcService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatsdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping("/fileDownload")
	public  Resource fileDownload( ItDamage itDamage) throws Exception { 
		 
		File file = new File("/SeoHan/ITDAMAGE/" + itDamage.getFileName());
		InputStream is = FileUtils.openInputStream(file);
		return new InputStreamResource(is);
	}
	
	@GetMapping( "/food")
	public @ResponseBody List<Food> FoodList(Dto dto) throws Exception {  
		List<Food> foodList = new ArrayList<Food>();
		foodList = etcService.foodList(dto);  
		return foodList;
	} 

	@GetMapping("/itdamage")
	public @ResponseBody List<ItDamage> iddamage() throws Exception { 
		List<ItDamage> damageList = new ArrayList<ItDamage>();  
		damageList = etcService.itDamage(); 
		return damageList;
	}

	@PutMapping("/itdamage/put")
	public void putItDamage(@RequestBody ItDamage itDamage ) throws Exception { 		
		etcService.putItDamage(itDamage ); 		
	}

	@PostMapping("/itdamage/post")
	public void postItDamage(@RequestBody ItDamage itDamage )  throws Exception { 		
		etcService.postItDamage(itDamage); 		
	}	
	
    // @Value("${temp.path}") private String tempPath; 
	
    // @RequestMapping(value = "/upload", method = RequestMethod.POST)
    // public List<MultipartFile> upload(MultipartFile[] files) {
	// 	return null;
  
    //     // PROCESS... 
	// }
	
	// @GetMapping("/{id}")
	// public Resource download(@PathVariable String id) throws IOException {
	// 	File file = new File(tempPath + id);
	// 	InputStream is = FileUtils.openInputStream(file);
	// 	return new InputStreamResource(is);
	// }
}
