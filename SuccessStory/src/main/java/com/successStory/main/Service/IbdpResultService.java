package com.successStory.main.Service;

import java.util.List;

import com.successStory.main.Payloads.IbdpResultDto;
import com.successStory.main.Payloads.MYP_ResultsDto;

public interface IbdpResultService {

	IbdpResultDto addIbdpResult(IbdpResultDto ibdpResultDto);
	List<IbdpResultDto> getAllIbdpResults();
    //IbdpResultDto getSingleIbdpResult(String student_Name);
	//IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String student_Name);
	//boolean deleteIbdpResult(IbdpResultDto ibdpResultDto,String student_Name);
	boolean deleteAllIbdpResult();
	//void deleteAllIbdpResult();
}

//Object getById(String student_name);
