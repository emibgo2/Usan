package com.example.usan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageDto<T,U,S>{
    int status;
    T data;
    U umbrella;
    S storage;
}
