package com.seohan.erp.mat.Controller;

import com.seohan.erp.mat.Dto.ImportPlanAlarm;
import com.seohan.erp.mat.Service.ImportPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/mat/import-plan/alarm") 
@RestController
class ImportPlanRestController {
	@Autowired
	private ImportPlanService importPlanService;

	@GetMapping("/user/{userid}")
	public ResponseEntity getImportPlanList(@PathVariable String userid) {
		return new ResponseEntity(importPlanService.getOmissionItemList(userid), HttpStatus.OK);
	}

//	@GetMapping("{udate}")
//	public @ResponseBody List<ImportPlan> getOneImportPlan(@PathVariable String udate) throws Exception {
//		return importPlanRepository.importPlanListbyUdate(udate);
//	}
}

