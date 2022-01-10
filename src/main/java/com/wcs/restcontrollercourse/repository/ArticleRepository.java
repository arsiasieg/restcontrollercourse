package com.wcs.restcontrollercourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.restcontrollercourse.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
