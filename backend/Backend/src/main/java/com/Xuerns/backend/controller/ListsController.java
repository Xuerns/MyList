package com.Xuerns.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Xuerns.backend.dto.ListsDto;
import com.Xuerns.backend.service.ListService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/list")
public class ListsController {

    private ListService listService;

    @PostMapping("/create")
    public ResponseEntity<ListsDto> createList(@RequestBody ListsDto listsDto) {
        ListsDto savedList = listService.createList(listsDto);
        return new ResponseEntity<>(savedList, HttpStatus.CREATED);
    }
}
