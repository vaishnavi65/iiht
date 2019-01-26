package com.programcreek.helloworld.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.programcreek.helloworld.model.Book;
import com.programcreek.helloworld.model.Subject;
import com.programcreek.helloworld.service.BookService;
import com.programcreek.helloworld.service.SubjectService;

@Controller
public class HelloWorldController {
	@Autowired
	BookService bookService;
	
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		ModelAndView mv=null;
		if(name.equalsIgnoreCase("AddBook"))
		{
			String message = "Please enter the book Details To Add";
		    mv = new ModelAndView("AddBook","book",new Book());
			mv.addObject("message", message);
			//mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("DeleteSubject"))
		{
			String message = "Please enter the Subject Name to delete";
			 mv = new ModelAndView("DeleteSubject","subject",new Subject());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("DeleteBook"))
		{
			String message = "Please enter the Book Name to delete";
			 mv = new ModelAndView("DeleteBook","book",new Book());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("SearchBook"))
		{
			String message = "Please enter the Book ID to search";
			 mv = new ModelAndView("SearchBook","book",new Book());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("SearchSubject"))
		{
			String message = "Please enter the Subject Name to search";
			 mv = new ModelAndView("SearchSubject","subject",new Subject());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("SearchBookByTitle"))
		{
			String message = "Please enter the Book Name to search";
			 mv = new ModelAndView("SearchBook","book",new Book());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("SearchSubjectByDuration"))
		{
			String message = "Please enter the Subject Duration to search";
			 mv = new ModelAndView("SearchSubject","subject",new Subject());
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		else if(name.equalsIgnoreCase("Exit"))
		{
			String message = "Please enter the Subject Name to delete";
			 mv = new ModelAndView("DeleteSubject");
				mv.addObject("message", message);
				mv.addObject("name", name);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("book")Book book, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		book=bookService.addABook(book);
		model.addAttribute("id", book.getBookId());
		model.addAttribute("title", book.getTitle());
        return "result";
    }
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String submitDeleteBook(@Valid @ModelAttribute("book")Book book, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		boolean resultDelete=false;
		try {
			resultDelete=bookService.deleteBook(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String searchRes="not deleted";
		if(resultDelete)
		{
			searchRes="deleted";
		}
		model.addAttribute("deleteRes", searchRes);
		return "deleteBookResult";
    }
	
	@RequestMapping(value = "/deleteSubject", method = RequestMethod.POST)
    public String submitDeleteSubject(@Valid @ModelAttribute("subject")Subject subject, 
      BindingResult result, ModelMap model) throws IOException {
		boolean resultDelete=false;
		try {
			resultDelete=subjectService.deleteSubject(subject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String searchRes="not deleted";
		if(resultDelete)
		{
			searchRes="deleted";
		}
		model.addAttribute("deleteRes", searchRes);
		return "deleteSubjectResult";
    }
	
	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
    public String submitSearchBook(@Valid @ModelAttribute("book")Book book, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		boolean resultSearch=bookService.searchBook(book);
		String searchRes="not Found";
		if(resultSearch)
		{
			searchRes="Found";
		}
		model.addAttribute("searchRes", searchRes);
		return "searchBookResult";
    }
	
	@RequestMapping(value = "/searchSubject", method = RequestMethod.POST)
    public String submitSerachSubject(@Valid @ModelAttribute("subject")Subject subject, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		boolean resultSearch=subjectService.searchSubject(subject);
		String searchRes="not Found";
		if(resultSearch)
		{
			searchRes="Found";
		}
		model.addAttribute("searchRes", searchRes);
		return "searchSubjectResult";
    }
	@RequestMapping(value = "/searchBookByTitle", method = RequestMethod.POST)
    public String submitSearchBookByTitle(@Valid @ModelAttribute("book")Book book, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		boolean resultSearch=bookService.searchBookByTitle(book);
		String searchRes="not Found";
		if(resultSearch)
		{
			searchRes="Found";
		}
		model.addAttribute("searchRes", searchRes);
		return "searchBookByTitleResult";
    }
	
	@RequestMapping(value = "/searchSubjectByDuration", method = RequestMethod.POST)
    public String submitSerachSubjectByDuration(@Valid @ModelAttribute("subject")Subject subject, 
      BindingResult result, ModelMap model) throws IOException, SQLException {
		boolean resultSearch=subjectService.serachSubjectByDuration(subject);
		String searchRes="not Found";
		if(resultSearch)
		{
			searchRes="Found";
		}
		model.addAttribute("searchRes", searchRes);
		return "serachSubjectByDurationResult";
    }
}
