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


import com.programcreek.helloworld.model.Book;
import com.programcreek.helloworld.model.Subject;
import com.programcreek.helloworld.repository.BookRepository;

@Service
public class BookService {
	static List<Book> bookList=new ArrayList<Book>();
	
	@Autowired
	BookRepository bookRepository;

	@Transactional
	public boolean loadBookDetails() {
		boolean loadedResult=false;
		try {
			bookList=bookRepository.findAll();
			loadedResult=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			loadedResult=false;
		}
		return loadedResult;
	}
	
	@Transactional
	public List<Book> loadBookDetails1() {
		List<Book> bookList1=new ArrayList<Book>();
		try {
			bookList1=bookRepository.findAll();
			//loadedResult=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//loadedResult=false;
		}
		return bookList1;
	}

	public boolean searchBook(Book book1) throws IOException, SQLException {
		boolean found=false;
		try {
			bookList=bookRepository.findAll();
			for(Book book:bookList)
			{
				if(book.getBookId()==book1.getBookId())
				{
					System.out.println("The required book is found . The subject Id is"+book.getBookId());
					found=true;
				}
			}
			if(!found)
			{
				System.out.println("The required book with the name "+book1.getTitle()+" is not found.");
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching the book "+e.getMessage());
			e.printStackTrace();
		}
		return found;
	}
	 
	public Book loadBookDetailsById(int id)
	{
			boolean found=false;
			Book resultBook=new Book();
			try {
				bookList=bookRepository.findAll();
				for(Book book:bookList)
				{
					if(book.getBookId()==id)
					{
						System.out.println("The required book is found . The subject Id is"+book.getBookId());
						found=true;
						return book;
					}
				}
				if(!found)
				{
					System.out.println("The required book with the name "+id+" is not found.");
				}
			} catch (Exception e) {
				System.out.println("Error occured while searching the book "+e.getMessage());
				e.printStackTrace();
			}
			return resultBook;
	}
	
	
	public boolean searchBookByTitle(Book book1) throws IOException, SQLException {
		boolean found=false;
		try {
			String id=null;
			id=bookRepository.findByTitle(book1.getTitle());
			if(id.equalsIgnoreCase(null))
			{
				found=true;
			}
		} catch (Exception e) {
			System.out.println("Error occured while searching the book "+e.getMessage());
			e.printStackTrace();
		}
		return found;
	}

	public boolean deleteBook(Book book) throws IOException, SQLException {
		boolean isDeleted=false;
		try {
			if(searchBook(book))
			{
				bookRepository.delete(book);
				System.out.println("The book has been deleted successfully");
				isDeleted=true;
				
			}
			
		} catch (Exception e) {
			System.out.println("Error occured while deleting the book "+e.getMessage());
			e.printStackTrace();
		}
		return isDeleted;				
	}
	
	public boolean deleteBookById(int id) throws IOException, SQLException {
		boolean isDeleted=false;
		try {
			Book bookTobeDeleted=loadBookDetailsById(id);
			if(bookTobeDeleted!=null)
			{
				bookRepository.delete(bookTobeDeleted);
				System.out.println("The book has been deleted successfully");
				isDeleted=true;
				
			}
			
		} catch (Exception e) {
			System.out.println("Error occured while deleting the book "+e.getMessage());
			e.printStackTrace();
		}
		return isDeleted;				
	}

	public Book addABook(Book book) throws IOException, SQLException {
		try {
			Long nextId=getTheNextBookId();
			nextId=nextId+1;
			book.setBookId(nextId);
			book.setPublishDate(new Date());
			bookRepository.save(book);
			System.out.println("The Book has been added.");
			
		} catch (Exception e) {
			System.out.println("Error occured while adding the record "+e.getMessage());
			e.printStackTrace();
		}
		return book;
	}
	
	public Boolean updateABook(Book book) throws IOException, SQLException {
		boolean found=false;
		try {
			found=searchBook(book);
			if(found)
			{
				bookRepository.save(book);
				System.out.println("The Book has been added.");
			}
			//nextId=nextId+1;
			
			
			
		} catch (Exception e) {
			System.out.println("Error occured while adding the record "+e.getMessage());
			e.printStackTrace();
		}
		return found;
	}

	public Long getTheNextBookId() {
		Long largest=0L;
		try {
			bookList=bookRepository.findAll();;
			for(Book book:bookList)
			{
				if(largest==0L)
				{
					largest=book.getBookId();
				}
				else
				{
					if(largest<book.getBookId())
					{
						largest=book.getBookId();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error while getting latest book id "+e.getMessage());
			e.printStackTrace();
		}
		return largest;
	}
	
	
}
