package com.successStory.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.successStory.main.Entities.IGCSE_Results;

@Repository
public interface IGCSE_ResultsRepo extends JpaRepository <IGCSE_Results, Integer> {

}
