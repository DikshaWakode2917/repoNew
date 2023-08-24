package com.successStory.main.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.backend.exceptions.ResourceNotFoundException;
import com.successStory.main.Dto.IbdpResultDtoToEntity;
import com.successStory.main.Dto.IbdpResultDtoToEntity.ibpdResultToDto;
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
    

//    @Override
//    public IbdpResultDto getSingleIbdpResult(IbpdResultToDto ibpdResultToDto,String student_Name) {
//        IbdpResult ibdpResult = this.ibdpResultRepo.findByStudentName(ibpdResultToDto, student_Name)
//        
//        if (ibdpResult != null) {
//            IbdpResultDto ibdpResultDto = this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult);
//            return ibdpResultDto;
//        } else {
//            // Handle the case when no result is found, for example, return null
//            return null;
//        }
//    }

//        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudent_Name(student_Name);
//
//        // Check if ibdpResults is not empty
//        if (!ibdpResults.isEmpty()) {
//            // Return the first result as an IbdpResultDto
//            return this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResults.get(0));
//        } else {
//            // Return null or handle the case when no result is found
//            return null; // You may want to handle this differently based on your use case.
//        }
   // }


//
//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String student_name) {
//        IbdpResult ibdpResult = this.ibdpResultRepo.findByStudentName(student_name);
//
//        if (ibdpResult == null) {
//            throw new ResourceNotFoundException("IbdpResult", "Student_name", student_name);
//        }
//
//        ibdpResult.setStudent_Name(ibdpResultDto.getStudent_Name());
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
//
//    @Override
//    public boolean deleteIbdpResult(String student_Name) {
//        // Find the IbdpResult by student_Name
//        IbdpResult ibdpResult = this.ibdpResultRepo.findByStudent_Name(student_Name);
//
//        if (ibdpResult == null) {
//            // If no result is found, throw an exception or return false, depending on your preference
//            throw new ResourceNotFoundException("IbdpResult", "Student_name", student_Name);
//            // Alternatively, you can return false here and handle it differently
//            // return false;
//        }

        // Delete the found IbdpResult
//        this.ibdpResultRepo.delete(ibdpResult);
//
//        // Return true to indicate successful deletion
//        return true;
//    }


    @Override
    public boolean deleteAllIbdpResult() {
        List<IbdpResult> ibdpResultsToDelete = this.ibdpResultRepo.findAll();

        if (ibdpResultsToDelete.isEmpty()) {
            return false;
        }

        this.ibdpResultRepo.deleteAll(ibdpResultsToDelete);
        return true;
    }

}