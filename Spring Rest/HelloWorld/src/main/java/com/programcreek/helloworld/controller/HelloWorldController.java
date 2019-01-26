package com.programcreek.helloworld.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.programcreek.helloworld.model.Book;
import com.programcreek.helloworld.model.Subject;
import com.programcreek.helloworld.service.BookService;
import com.programcreek.helloworld.service.SubjectService;

@RestController
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
	
	@RequestMapping(value ="/rest/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		books=bookService.loadBookDetails1();
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping("/book")
	public Book book(@RequestParam(value="name", defaultValue="World") String name) {
		
		return bookService.loadBookDetailsById(1) ;
	}
	
	
	@RequestMapping(value ="/rest/book/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBookByBookId(@PathVariable("id") int id) {
		Book book = new Book();
		book=bookService.loadBookDetailsById(id);
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/rest/AddBook", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> addBook(@RequestBody Book book1) throws IOException, SQLException {
		Book book = new Book();
		book=bookService.addABook(book1);
		return new ResponseEntity<Book>(book,HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="/rest/DelBook/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteBook(@PathVariable("id") int id) throws IOException, SQLException {
		//Book book = new Book();
		boolean res=bookService.deleteBookById(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value ="/rest/AlterBook", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity alterBook(@RequestBody Book book1) throws IOException, SQLException {
		//Book book = new Book();
		boolean added=bookService.updateABook(book1);
		return new ResponseEntity(added,HttpStatus.CREATED);
	}
}
