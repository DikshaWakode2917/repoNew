package com.successStory.main.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.backend.exceptions.ResourceNotFoundException;
import com.successStory.main.Dto.IbdpResultDtoToEntity;
import com.successStory.main.Entities.IbdpResult;
import com.successStory.main.Payloads.IbdpResultDto;
import com.successStory.main.Repositories.IbdpResultRepo;
import com.successStory.main.Service.IbdpResultService;

@Service
public class IbdpResultServiceImpl implements IbdpResultService {

    private final IbdpResultRepo ibdpResultRepo;
    private final IbdpResultDtoToEntity ibdpResultDtoToEntity;

    @Autowired
    public IbdpResultServiceImpl(IbdpResultRepo ibdpResultRepo, IbdpResultDtoToEntity ibdpResultDtoToEntity) {
        this.ibdpResultRepo = ibdpResultRepo;
        this.ibdpResultDtoToEntity = ibdpResultDtoToEntity;
    }

    @Override
    public IbdpResultDto addIbdpResult(IbdpResultDto ibdpResultDto) {
        IbdpResult ibdpResult = ibdpResultDtoToEntity.dtoToIbdpResult(ibdpResultDto);
        ibdpResult = ibdpResultRepo.save(ibdpResult);
        ibdpResultDto = ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult);
        return ibdpResultDto;
    }


    @Override
   public List<IbdpResultDto> getAllIbdpResults() {
        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findAll();
        List<IbdpResultDto> ibdpResultDto = ibdpResults.stream()
                .map(ibdpResult -> this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult))
                .collect(Collectors.toList());
        return ibdpResultDto;
    }
    
    @Override
    public List<IbdpResultDto> findByStudentName(String studentName) {
    	List<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudentName(studentName);
    	List<IbdpResultDto> ibdpResultDto = ibdpResults.stream()
                .map(ibdpResult -> this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult))
                .collect(Collectors.toList());
    	return ibdpResultDto;
    }

    @Override
    public boolean deleteAllIbdpResult(){
    	List<IbdpResult> deleteIbdpResults = this.ibdpResultRepo.findAll();
    	if (deleteIbdpResults.isEmpty()) {
    		return false;
    	}
    	else this.ibdpResultRepo.deleteAll(deleteIbdpResults);
    	return true;
    }


    @Override
    public boolean deleteSingleIbdpResult(String studentName) {
        // Retrieve the list of IbdpResult entities with the given studentName
        List<IbdpResult> ibdpResultsToDelete = this.ibdpResultRepo.findByStudentName(studentName);

        // Check if any matching entities were found
        if (!ibdpResultsToDelete.isEmpty()) {
            // Assuming you want to delete the first matching entity, you can do:
            IbdpResult ibdpResultToDelete = this.ibdpResultRepo.deleteByStudentName(studentName);

            // Delete the entity

            // Return true to indicate that a matching entity was deleted
            return true;
        } else {
            // Return false to indicate that no matching entity was found and deleted
            return false;
        }
    }

    

//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
//        List<IbdpResult> ibdpResult = this.ibdpResultRepo.findByStudentName(studentName);
//        if (ibdpResult == null) {
//            throw new ResourceNotFoundException("IbdpResult", "Student_name", studentName);
//       }
//
//        ibdpResult.setStudentName(ibdpResultDto.getStudentName());
//        ibdpResult.setSchool_Name(ibdpResultDto.getSchool_Name());
//        ibdpResult.setYear(ibdpResultDto.getYear());
//        ibdpResult.setLevel(ibdpResultDto.getLevel());
//        ibdpResult.setScore(ibdpResultDto.getScore());
//        ibdpResult.setIA_Score(ibdpResultDto.getIA_Score());
//        ibdpResult.setStatus(ibdpResultDto.isStatus());
//
//        IbdpResult updatedIbdpResult = this.ibdpResultRepo.save(ibdpResult);
//        IbdpResultDto ibdpResultDto1 = this.ibdpResultDtoToEntity.ibpdResultToDto(updatedIbdpResult);
//        return ibdpResultDto1;
//    }
    
    
  
//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
//        // Find the IbdpResult entity by studentName
//        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudentName(studentName);
//
//        // Check if any matching entities were found
//        if (ibdpResults.isEmpty()) {
//            throw new ResourceNotFoundException("IbdpResult", "studentName", studentName);
//        }
//
//        // Assuming you want to update the first matching entity, you can do:
//        IbdpResult ibdpResultToUpdate = (IbdpResult) this.ibdpResultRepo.findByStudentName(studentName);
//
//        // Update the entity with values from the DTO
//        ibdpResultToUpdate.setStudentName(ibdpResultDto.getStudentName());
//        ibdpResultToUpdate.setSchool_Name(ibdpResultDto.getSchool_Name());
//        ibdpResultToUpdate.setYear(ibdpResultDto.getYear());
//        ibdpResultToUpdate.setLevel(ibdpResultDto.getLevel());
//        ibdpResultToUpdate.setScore(ibdpResultDto.getScore());
//        ibdpResultToUpdate.setIA_Score(ibdpResultDto.getIA_Score());
//        ibdpResultToUpdate.setStatus(ibdpResultDto.isStatus());
//
//        // Save the updated entity
//        IbdpResult updatedIbdpResult = this.ibdpResultRepo.save(ibdpResultToUpdate);
//
//        // Convert the updated entity back to DTO and return it
//        return this.ibdpResultDtoToEntity.ibpdResultToDto(updatedIbdpResult);
//    }

   
    @Override
    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
        // Find the IbdpResult entities by studentName
        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudentName(studentName);

        // Check if any matching entities were found
        if (ibdpResults.isEmpty()) {
            throw new ResourceNotFoundException("IbdpResult", "studentName", studentName);
        }

        // Iterate through the list of matching entities and update each one
        for (IbdpResult ibdpResultToUpdate : ibdpResults) {
            ibdpResultToUpdate.setStudentName(ibdpResultDto.getStudentName());
            ibdpResultToUpdate.setSchool_Name(ibdpResultDto.getSchool_Name());
            ibdpResultToUpdate.setYear(ibdpResultDto.getYear());
            ibdpResultToUpdate.setLevel(ibdpResultDto.getLevel());
            ibdpResultToUpdate.setScore(ibdpResultDto.getScore());
            ibdpResultToUpdate.setIA_Score(ibdpResultDto.getIA_Score());
            ibdpResultToUpdate.setStatus(ibdpResultDto.isStatus());

            // Save the updated entity
            this.ibdpResultRepo.save(ibdpResultToUpdate);
        }

        return this.ibdpResultDtoToEntity.ibpdResultToDto((IbdpResult) ibdpResults);
    }

}