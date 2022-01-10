package com.wcs.restcontrollercourse.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.restcontrollercourse.dto.ArticleDto;
import com.wcs.restcontrollercourse.model.Article;
import com.wcs.restcontrollercourse.repository.ArticleRepository;

@RestController
@RequestMapping("/articles") //permet d'avoir un route de base. Toutes les autres se baseront dessus
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;

	// Get all articles
	@GetMapping
	public List<Article> getArticles(){
		return articleRepository.findAll();
	}
	
	// Get one article
	@GetMapping("/{id}")
	public Article getArticle(@PathVariable(required = true) Long id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if(optionalArticle.isPresent()) {
			return optionalArticle.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");

		}
	}
	
	// Create article
	@PostMapping
	public Article createArticle(@Valid @RequestBody ArticleDto articleDto) {
		Article article = new Article();
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCreation(new Date());
		
		return articleRepository.save(article);
	}
	
	// Update article
	@PutMapping
	public Article updateArticle(@PathVariable Long id, @RequestParam String title, @RequestParam String content, @RequestParam Date creation) {
		Article articleToUpdate = articleRepository.getById(id);
		
		if(title !=null) {
			articleToUpdate.setTitle(title);
		}
		
		if(content !=null) {
			articleToUpdate.setContent(content);
		}
		
		if(creation !=null) {
			articleToUpdate.setCreation(creation);
		}
		
		return articleRepository.save(articleToUpdate);
	}
	
	// Delete article
	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable(required = true) Long id) {
		articleRepository.deleteById(id);
	}
	
}
