package com.seohan.erp.base.Controller;

import com.seohan.erp.base.Domain.Code;
import com.seohan.erp.base.Repository.CodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/base/code")
@Slf4j
@RestController
class CodeRestController {
	@Autowired
	private CodeRepository codeRepo;

	@GetMapping
	public ResponseEntity getAllList() throws Exception {
		return  new ResponseEntity(codeRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("{adgub}")
	public ResponseEntity getCodeByAdgub(@PathVariable String adgub) throws Exception {
		return  new ResponseEntity(codeRepo.findByAdgub(adgub), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity  updateCode(@RequestBody Code itDamage ) throws Exception {
		return new ResponseEntity(codeRepo.save(itDamage ), HttpStatus.OK);
	}

//	@PostMapping("save")
//	public ResponseEntity<Void> createCode(@RequestBody Code Code )  throws Exception {
//		Code CodeCreated= CodeService.save(Code );
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{rtime}"	).buildAndExpand(CodeCreated.getRtime()).toUri();
//		return   ResponseEntity.created(uri).build();
//	}

	@GetMapping("/fact")
	public ResponseEntity getFact() throws Exception {
		return new ResponseEntity(codeRepo.findFact(), HttpStatus.OK);
	}
}
