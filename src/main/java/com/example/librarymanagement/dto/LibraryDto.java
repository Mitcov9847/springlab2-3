package com.example.librarymanagement.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {
    private Long id;
    private String location;
    private List<Long> bookIds;
}
