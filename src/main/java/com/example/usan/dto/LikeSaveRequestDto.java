package com.example.usan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeSaveRequestDto {
    private Long userId;
    private Long boardId;
}

