package com.programcreek.helloworld.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.programcreek.helloworld.model.Subject;
import com.programcreek.helloworld.model.Subject;
import com.programcreek.helloworld.repository.SubjectRepository;

@Service
public class SubjectService {
	 List<Subject> subjectList=new ArrayList<Subject>();
	
	@Autowired
	SubjectRepository subjectRepository;

	@Transactional
	public boolean loadSubjectAndSubjectDetails() {
		boolean loadedResult=false;
		try {
			subjectList=subjectRepository.findAll();
			loadedResult=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			loadedResult=false;
		}
		return loadedResult;
	}

	
	public  boolean searchSubject(Subject subject1) throws IOException, SQLException {
		boolean found=false;
		
		try {
			subjectList=subjectRepository.findAll();
			for(Subject subject:subjectList)
			{
				if(subject.getSubTitle().equalsIgnoreCase(subject1.getSubTitle()))
				{
					System.out.println("The required Subject is found . The subject Id is"+subject1.getSubjectId());
					found=true;
				}
			}
			if(!found)
			{
				System.out.println("The required Subject with the name "+subject1.getSubTitle()+" is not found.");
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching the subject "+e.getMessage());
			e.printStackTrace();
		}
		return found;
	}

	public boolean deleteSubject(Subject subject) throws IOException, SQLException {
		boolean isDeleted=false;
		
		try {
			if(searchSubject(subject))
			{
				subjectRepository.delete(subject);
				isDeleted=true;
			}
			
		} catch (Exception e) {
			System.out.println("Error occured while deleting the subject "+e.getMessage());
			e.printStackTrace();
		}
		return isDeleted;
	}

	public  boolean serachSubjectByDuration(Subject subject1) throws IOException, SQLException {
		boolean found=false;
		
		try {
			String title=null;
			title=subjectRepository.findSubTitleByDuration(subject1.getDurationInHours());
			if(title!=null)
			{
				found=true;
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching the subject "+e.getMessage());
			e.printStackTrace();
		}
		return found;
	}
	
}
