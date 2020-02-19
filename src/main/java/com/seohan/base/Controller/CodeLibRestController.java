package com.seohan.base.Controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.seohan.base.Domain.CodeLib;
import com.seohan.base.Mapper.CodeLibRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j; 

@RequestMapping("/base/codeLib")
@Slf4j 
@RestController
class CodeLibRestController { 
	
//	@Autowired
//	private CodeLibService codeLibService;
	@Autowired
	private CodeLibRepository codeLibRepo;
 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatsdf = new SimpleDateFormat("yyyy-MM-dd"); 
	 
	@GetMapping("")
	public @ResponseBody List<CodeLib> getAllList() throws Exception { 
		return codeLibRepo.findAll(); 
	}

	@GetMapping("{adgub}")
	public @ResponseBody List<CodeLib> getCodeLibByAdgub(@PathVariable String adgub) throws Exception { 
		return codeLibRepo.findByAdgub(adgub);
	} 	
	
	@PutMapping("")
	public ResponseEntity<CodeLib> updateCodeLib(@RequestBody CodeLib itDamage ) throws Exception { 		
		CodeLib itDamageUpdated = codeLibRepo.save(itDamage ); 
		return new ResponseEntity<CodeLib>(itDamageUpdated, HttpStatus.OK);
	}

//	@PostMapping("save")
//	public ResponseEntity<Void> createCodeLib(@RequestBody CodeLib codeLib )  throws Exception { 		
//		CodeLib codeLibCreated= codeLibService.save(codeLib ); 
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{rtime}"	).buildAndExpand(codeLibCreated.getRtime()).toUri();
//		return   ResponseEntity.created(uri).build();
//	}	 

	@GetMapping("/fact")
	public @ResponseBody List<CodeLib> getFact() throws Exception { 
		return codeLibRepo.findFact();
	}
}
