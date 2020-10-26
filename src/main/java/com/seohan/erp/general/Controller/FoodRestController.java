package com.seohan.erp.general.Controller;

import com.seohan.erp.general.Domain.MngFood;
import com.seohan.erp.general.Repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/general/food")
@Slf4j
@RestController
class FoodRestController {
	@Autowired
	private FoodRepository foodRepo;

	@GetMapping
	public @ResponseBody List<MngFood> FoodTable(@RequestParam String gdate, @RequestParam String companycode) {
		List<MngFood> foods = foodRepo.findByCompanycodeAndGdate(companycode, gdate);
		return foods;
	}

	@GetMapping("/{companycode}/date/{gdate}")
	public ResponseEntity FoodTableLab(@PathVariable String gdate, @PathVariable String companycode) throws Exception {
		return new ResponseEntity(foodRepo.findByCompanycodeAndGdate(companycode, gdate), HttpStatus.OK);
	}

}
