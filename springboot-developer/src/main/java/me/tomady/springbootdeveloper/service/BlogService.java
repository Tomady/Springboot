package me.tomady.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.tomady.springbootdeveloper.domain.Article;
import me.tomady.springbootdeveloper.dto.AddArticleRequest;
import me.tomady.springbootdeveloper.dto.UpdateArticleRequest;
import me.tomady.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
