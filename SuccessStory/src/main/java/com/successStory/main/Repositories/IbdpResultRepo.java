package com.successStory.main.Repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.successStory.main.Entities.IbdpResult;

@Repository("ibdpResultRepo")
public interface IbdpResultRepo extends JpaRepository <IbdpResult, Integer> {

	//IbdpResult findByStudent_Name(String Student_Name);
	@Query("select result from Ibdp_Result result WHERE result.StudentName = :studentName")
	IbdpResult findByStudentName(String studentName);

	
	

	//Object getStudent_id(String student_name);

	//	@Query("SELECT r.studentName FROM IbdpResult r WHERE r.studentName = :studentName1")
	//	IbdpResult findByStudentName(@Param("studentName1") String studentName1);

}
