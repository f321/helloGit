package com.jdbcexample.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jdbcexample.domain.Post;
import com.jdbcexample.domain.PostCrud;


@Controller
@RequestMapping("/posts")
public class Controllers {

	
	PostCrud postCrud;
	
	@RequestMapping("/")
	public String pocetnaStranica(Model model) {
		postCrud = new PostCrud();
		model.addAttribute("posts", postCrud.Ucitaj());
		System.out.println("Pozz");
		return "posts/list";
	}
	
	@RequestMapping("/new")
	public String novaPoruka() {
		return "posts/new";
	}
	
	@RequestMapping("/create")
	public ModelAndView unosPoruke(@RequestParam("message") String poruka) throws Exception {
		com.jdbcexample.domain.Post p = new com.jdbcexample.domain.Post();
		postCrud = new PostCrud();
		p.setMessage(poruka);
		postCrud.unesi(p);
		return new ModelAndView("redirect:/posts");
	}
	
	@RequestMapping("/{id}/edit")
	public String update(@PathVariable int id, Model m) {
		
		Post p = postCrud.pronadji(id);
		m.addAttribute("post", p);
		return "posts/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView edit(@RequestParam("post_id") int id, @RequestParam("message") String m) throws SQLException {
		Post p = postCrud.pronadji(id);
		p.setMessage(m);
		postCrud.update(p);
		return new ModelAndView("redirect:/posts");
	}
	
	@RequestMapping("{id}/delete")
	public ModelAndView obrisi(@PathVariable int id) throws SQLException {
		postCrud.obrisi(id);
		return new ModelAndView("redirect:/posts");
	}
}
