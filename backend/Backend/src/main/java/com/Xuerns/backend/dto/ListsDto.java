package com.Xuerns.backend.dto;

import com.Xuerns.backend.model.enums.TypeList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListsDto {
    private Long id;
    private UsersDto usersdDto;
    private String name;
    private TypeList typeList;
}
