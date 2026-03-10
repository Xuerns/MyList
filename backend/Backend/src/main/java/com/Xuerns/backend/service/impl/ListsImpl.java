package com.Xuerns.backend.service.impl;

import org.springframework.stereotype.Service;

import com.Xuerns.backend.dto.ListsDto;
import com.Xuerns.backend.dto.UsersDto;
import com.Xuerns.backend.entity.Lists;
import com.Xuerns.backend.entity.Users;
import com.Xuerns.backend.repository.ListsRepository;
import com.Xuerns.backend.repository.UsersRepository;
import com.Xuerns.backend.service.ListService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListsImpl implements ListService {
    
    private ListsRepository listRepository;
    private UsersRepository usersRepository;

    @Override
    public ListsDto createList(ListsDto listsDto) {
        
        Lists lists = new Lists();
        lists.setName(listsDto.getName());

        Long usersId = listsDto.getUsersdDto().getId();

        Users users = usersRepository.findById(usersId).orElseThrow(() -> new RuntimeException("Cannot find user"));
        lists.setUser(users);

        Lists savedList = listRepository.save(lists);

        ListsDto resList = new ListsDto();
        resList.setId(savedList.getId());
        resList.setName(savedList.getName());
        
        UsersDto resUsersDto = new UsersDto();
        resUsersDto.setId(savedList.getUser().getId());
        resList.setUsersdDto(resUsersDto);
        return resList;
    }

}
