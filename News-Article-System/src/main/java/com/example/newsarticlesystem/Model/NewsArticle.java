package com.example.newsarticlesystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 4,max = 100)
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min = 4,max = 20)
    private String author;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 200)
    private String content;

    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "politics|sports|technology", message = "Must be either \"politics\", \"sports\" or \"technology\" only.")
    private String category;

    @NotEmpty(message = "URL image must be not empty")
    private String imageUrl;

    private boolean isPublished = false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;



}
