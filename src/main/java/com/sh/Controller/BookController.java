package com.sh.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sh.Domain.Book;

@Controller
@RequestMapping("/")
public class BookController {  
 
    @RequestMapping(value = "/hello")
	public String hello() { 
		return "hello";
	}

    @RequestMapping("/index")
    public String velocity() {
        return "index";
    }
    
    @GetMapping("/bookList")
	String bookList(@RequestParam(name="queryType", required=false, defaultValue="all") String queryType, Book book, Model model) throws Exception {
//    	book = bookMapper.selectOne(); 
    	model.addAttribute("name", book.getName());
		return "book/hello";
	} 
}
