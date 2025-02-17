package me.tomady.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.tomady.springbootdeveloper.domain.Article;
import me.tomady.springbootdeveloper.dto.AddArticleRequest;
import me.tomady.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

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
}
