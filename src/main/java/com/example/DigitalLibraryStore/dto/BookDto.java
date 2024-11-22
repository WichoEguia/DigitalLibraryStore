package com.example.DigitalLibraryStore.dto;

import com.example.DigitalLibraryStore.utils.enums.Format;
import com.example.DigitalLibraryStore.utils.annotations.MinPages;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record BookDto(
        String isbn,
        String title,
        String description,
        String imageUrl,
        Format format,
        LocalDate publishDate,
        @MinPages(value = 100, message = "${message.books.pagesNo.invalid_pages_number}")
        Integer pagesNo,
        @Min(value = 0, message = "${message.books.popularity.invalid_min}")
        @Max(value = 10, message = "${message.books.popularity.invalid_min}")
        double popularity
) {
}
