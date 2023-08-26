package com.successStory.main.Payloads;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.successStory.main.Entities.MYP_Results;
import com.successStory.main.Repositories.MYP_ResultsRepo;
import com.successStory.main.Service.MYP_ResultsService;

@CrossOrigin
@RestController
@RequestMapping("/api/myp_results")
@Component
public class MYP_ResultsController {

	@Autowired
	MYP_ResultsService myp_resultsService;
	
	@Autowired
	MYP_ResultsRepo myp_resultsRepo;
	@PostMapping
	public ResponseEntity<MYP_ResultsDto> aaddMYP_Result(@RequestBody MYP_ResultsDto myp_resultsDto) {
		
		MYP_ResultsDto aaddedMYP_Result = this.myp_resultsService.addMYP_Result(myp_resultsDto);
		return ResponseEntity.ok(aaddedMYP_Result);
	}
	
	@GetMapping
	public ResponseEntity<List<MYP_ResultsDto>> getAllMYP_Results() {
		return ResponseEntity.ok(this.myp_resultsService.getAllMYP_Results());
	}
	
	@DeleteMapping()
	public List<MYP_ResultsDto> deleteAllMYP_Results() {
		List<MYP_ResultsDto> deletedAllMYP_Results = this.myp_resultsService.getAllMYP_Results();
		
		this.myp_resultsService.deleteAllMYP_Results();
		return deletedAllMYP_Results;
	}
}
