package com.seohan.erp.mat.Controller;

import com.seohan.erp.mat.Domain.LocaAlmEntity;
import com.seohan.erp.mat.Repository.LocaAlmEntityRepository;
import com.seohan.erp.mat.Service.LocaAlmEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mat/locaalm")
@Slf4j
@RestController
class LocaAlmRestController {
	@Autowired
	private LocaAlmEntityService locaAlmEntityService;
	@Autowired
	private LocaAlmEntityRepository locaAlmEntityRepository;

	@GetMapping
	public ResponseEntity<List<LocaAlmEntity>> getLocaAlmList() {
		return new ResponseEntity<List<LocaAlmEntity>>(locaAlmEntityRepository.findLocaAlmEntityByGubnAndSts("A2","1"), HttpStatus.OK);
	}

	@GetMapping("params")
	public ResponseEntity getDataWithParams(Pageable pageable, @RequestParam String gubn, @RequestParam String sts) {
		return new ResponseEntity(locaAlmEntityRepository.findLocaAlmEntityByGubnAndSts(gubn,sts), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<List<LocaAlmEntity>> endLocaAlmEntity(@RequestBody List<LocaAlmEntity> locaAlmEntitys) {
		return new ResponseEntity<List<LocaAlmEntity>>(locaAlmEntityService.endLocaAlmEntity(locaAlmEntitys), HttpStatus.OK);
	}
}
