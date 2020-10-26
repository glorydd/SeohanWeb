package com.seohan.erp.base.Controller;

import com.seohan.erp.base.Domain.LineView;
import com.seohan.erp.base.Repository.LineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/base/line")
@Slf4j
@RestController
class LineRestController {
	@Autowired
	private LineRepository lineRepository;

	@GetMapping
	public @ResponseBody List<LineView> getAllList(@RequestParam String actgb) throws Exception {
		return lineRepository.findByActgb(actgb);
	}

	@GetMapping("/fact/{factory}")
	public @ResponseBody List<LineView> getListByFactory(@PathVariable String factory) throws Exception {
		return lineRepository.findByFact (factory);
	}

	@GetMapping("{wrkct}")
	public @ResponseBody LineView getOneLine(@PathVariable String wrkct) throws Exception {
		return lineRepository.findByWrkct(wrkct);
	}

//	@PutMapping("save")
//	public ResponseEntity<Line> updateLine(@PathVariable String udate, @PathVariable String ser, @RequestBody Line report ) throws Exception {
//		Line reportUpdated = lineService.save(report );
//		return new ResponseEntity<Line>(reportUpdated, HttpStatus.OK);
//	}

//	@PostMapping("")
//	public ResponseEntity<Void> createLine(@RequestBody Line report )  throws Exception {
//		Line reportCreated= lineService.save(report );
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{udate}"	).buildAndExpand(reportCreated.getUdate()).toUri();
//		return   ResponseEntity.created(uri).build();
//	}
}
