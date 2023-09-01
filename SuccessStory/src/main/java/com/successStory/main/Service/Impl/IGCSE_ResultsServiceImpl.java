package com.successStory.main.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.backend.exceptions.ResourceNotFoundException;
import com.successStory.main.Dto.IGCSE_ResultsDtoToEntity;
import com.successStory.main.Entities.IGCSE_Results;
import com.successStory.main.Payloads.IGCSE_ResultsDto;
import com.successStory.main.Repositories.IGCSE_ResultsRepo;
import com.successStory.main.Service.IGCSE_ResultsService;

@Service
public class IGCSE_ResultsServiceImpl implements IGCSE_ResultsService{

	private final IGCSE_ResultsRepo igcse_resultsRepo;
	private final IGCSE_ResultsDtoToEntity igcse_resultsDtoToEntity;
	
	@Autowired
	public IGCSE_ResultsServiceImpl(IGCSE_ResultsRepo igcse_resultsRepo, IGCSE_ResultsDtoToEntity igcse_resultsDtoToEntity) {
		super();
		this.igcse_resultsRepo = igcse_resultsRepo;
		this.igcse_resultsDtoToEntity = igcse_resultsDtoToEntity;
	}

	@Override
	public IGCSE_ResultsDto addIGCSE_Results(IGCSE_ResultsDto igcse_resultsDto) {
		
		IGCSE_Results igcse_Result = igcse_resultsDtoToEntity.dtoToIGCSEResult(igcse_resultsDto);
		igcse_Result = igcse_resultsRepo.save(igcse_Result);
		igcse_resultsDto = igcse_resultsDtoToEntity.igcseResultToDto(igcse_Result);
		return igcse_resultsDto;
	}
	
	@Override
	public List<IGCSE_ResultsDto> getAllIGCSE_Results(){
		List<IGCSE_Results> igcse_results = this.igcse_resultsRepo.findAll();
		List<IGCSE_ResultsDto> igcse_resultsDto = igcse_results.stream().map(results -> this.igcse_resultsDtoToEntity.igcseResultToDto(results)).collect(Collectors.toList());
		return igcse_resultsDto;

	}
	
	@Override
	public List<IGCSE_ResultsDto> findResultsByStudentName(String studentName){
		List<IGCSE_Results> igcse_results = this.igcse_resultsRepo.findByStudentName(studentName);
		List<IGCSE_ResultsDto> igcse_resultsDto = igcse_results.stream().map(results -> this.igcse_resultsDtoToEntity.igcseResultToDto(results)).collect(Collectors.toList());
		return igcse_resultsDto;
	}
	@Override
	public boolean deleteAllIGCSE_Results() {
		List<IGCSE_Results> igcse_resultsToDelete = this.igcse_resultsRepo.findAll();
		if (igcse_resultsToDelete.isEmpty()) {
			return false;
		}
		else this.igcse_resultsRepo.deleteAll(igcse_resultsToDelete);
		return true;
	}
	
	@Override
	public boolean deleteSingleIGCSE_Results(String studentName) {
		List<IGCSE_Results> igcse_resultsToDelete = this.igcse_resultsRepo.findByStudentName(studentName);
		
		if (!igcse_resultsToDelete.isEmpty()) {
			this.igcse_resultsRepo.deleteByStudentName(studentName);
			return true;
		}
		else {
			return false;
		}
	}
		
	@Override
	public List<IGCSE_Results> updateIGCSE_Results(IGCSE_ResultsDto igcse_resultsDto, String studentName) {
		
		List<IGCSE_Results> igcse_results = this.igcse_resultsRepo.findByStudentName(studentName);
		
		if(igcse_results.isEmpty()) {
			throw new ResourceNotFoundException("IGCSE_Results","student_Name",studentName);
		}
		for(IGCSE_Results igcse_resultsToUpdate : igcse_results) {
			igcse_resultsToUpdate.setStudentName(igcse_resultsDto.getStudentName());
			igcse_resultsToUpdate.setStudent_School(igcse_resultsDto.getStudent_School());
			igcse_resultsToUpdate.setYear(igcse_resultsDto.getYear());
			igcse_resultsToUpdate.setExtented(igcse_resultsDto.getExtented());
			igcse_resultsToUpdate.setAdditional(igcse_resultsDto.getAdditional());
			igcse_resultsToUpdate.setInternational(igcse_resultsDto.getInternational());
			igcse_resultsToUpdate.setStatus(igcse_resultsDto.isStatus());
			
			this.igcse_resultsRepo.save(igcse_resultsToUpdate);	
		}
	return (List<IGCSE_Results>) this.igcse_resultsDtoToEntity.igcseResultToDto((IGCSE_Results) igcse_results);
	}

	

	
	
	
	
	
	
	
	
	
		
	
}
