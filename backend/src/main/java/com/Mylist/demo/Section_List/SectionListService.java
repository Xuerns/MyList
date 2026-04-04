package com.Mylist.demo.Section_List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mylist.demo.Section_List.dto.SectionRequest;
import com.Mylist.demo.Users.Users;
import com.Mylist.demo.Users.UsersRepository;

@Service
public class SectionListService {
    @Autowired
    private SectionListRepository sectionListRepository;
    @Autowired
    private UsersRepository usersRepository;

    public SectionList createSectionList(SectionRequest request, String email) {
        Users user = usersRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User Not Found"));

        SectionList sectionList = new SectionList();
        sectionList.setTitle(request.getTitle());
        sectionList.setUser(user);
        return sectionListRepository.save(sectionList);
    }

    public List<SectionList> getSectionLists(String email) {
        Users user = usersRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User Not Found"));
        return sectionListRepository.findByUser(user);
    }
}
