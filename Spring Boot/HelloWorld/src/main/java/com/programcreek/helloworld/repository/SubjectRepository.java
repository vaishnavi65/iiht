package com.programcreek.helloworld.repository;

import java.util.List;

import com.programcreek.helloworld.model.Subject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
	
	List<Subject> findAll();

	@Query("SELECT t.subTitle FROM Subject t where t.durationInHours = :durationInHours") 
    String findSubTitleByDuration(@Param("durationInHours") Integer durationInHours);
}
