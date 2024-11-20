package com.example.newsarticlesystem.NewsArticleController;

import com.example.newsarticlesystem.ApiResponse.ApiResponse;
import com.example.newsarticlesystem.Model.NewsArticle;
import com.example.newsarticlesystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news-article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNews(){
        ArrayList news = newsArticleService.getNews();
        return ResponseEntity.status(200).body(news);
    }


    @PostMapping("/add")
    public ResponseEntity addNews(@RequestBody @Valid NewsArticle news, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNews(news);
        return ResponseEntity.status(200).body(new ApiResponse("News Article added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNews(@PathVariable String id, @RequestBody @Valid NewsArticle news, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate = newsArticleService.updateNews(id,news);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("News Article updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNews(@PathVariable String id){
        boolean isDelete=newsArticleService.deleteNews(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("News Article deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity updatePublish(@PathVariable String id){
        boolean isPublish = newsArticleService.updatePublish(id);
        if (isPublish){
            return ResponseEntity.status(200).body(new ApiResponse("News Article published successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
    }

    @GetMapping("/published")
    public ResponseEntity allPublish(){
        ArrayList news = newsArticleService.allPublish();
        return ResponseEntity.status(200).body(news);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity searchByCategory(@PathVariable String category){
        ArrayList<NewsArticle> foundCategory = newsArticleService.searchByCategory(category);
        if (foundCategory == null){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid category. Must be either politics, sports, or technology."));
        }
        return ResponseEntity.status(200).body(foundCategory);
    }


}
