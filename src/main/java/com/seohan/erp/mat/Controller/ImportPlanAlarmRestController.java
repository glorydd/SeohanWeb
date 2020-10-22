package com.seohan.erp.mat.Controller;

import com.seohan.erp.mat.Service.ImportPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mat/importplanalarm")
@Slf4j
@RestController
class ImportPlanAlarmRestController {
	@Autowired
	private ImportPlanService importPlanService;

	@GetMapping("/user/{userid}")
	public ResponseEntity getOmissionItemList(@PathVariable String userid) {
		return new ResponseEntity (importPlanService.getOmissionItemList(userid), HttpStatus.OK);
	}

//	@GetMapping("{udate}")
//	public @ResponseBody List<ImportPlan> getOneImportPlan(@PathVariable String udate) throws Exception {
//		return importPlanRepository.importPlanListbyUdate(udate);
//	}
}
