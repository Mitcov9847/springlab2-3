package com.example.librarymanagement.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;

    private Long authorId;
    private Long publisherId;
    private List<Long> categoryIds;
}
