package com.Mylist.demo.Section_List;

// Next : Validate Null / Empty Values in Request DTOs

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

    public SectionList updateSectionList(Long sectionListId, SectionRequest request, String email) {
        SectionList updatedSectionList = sectionListRepository.findById(sectionListId)
            .orElseThrow(() -> new RuntimeException("Section List Not Found"));

        if (!updatedSectionList.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }

        updatedSectionList.setTitle(request.getTitle());
        return sectionListRepository.save(updatedSectionList);
    }

    public String deleteSectonList(Long sectionListId, String email) {
        SectionList deletedSectionList = sectionListRepository.findById(sectionListId)
            .orElseThrow(() -> new RuntimeException("Section List Not Found"));

        if (!deletedSectionList.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }
        sectionListRepository.delete(deletedSectionList);
        return "Section List " + deletedSectionList.getTitle() + " Deleted Successfully";
    }
}
