package com.Mylist.demo.Section_List;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Mylist.demo.Section_List.dto.SectionRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/api/SectionLists")
@CrossOrigin(origins = "*")
public class SectionListController {
    @Autowired
    private SectionListService sectionListService;

    @PostMapping
    public ResponseEntity<?> createSectionList(@RequestBody SectionRequest request, Principal principal) {
        String email = principal.getName();
        try {
            SectionList newSectionList = sectionListService.createSectionList(request, email);
            return ResponseEntity.ok(newSectionList);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getSectionList(Principal principal) {
        String email = principal.getName();
        try {
            List<SectionList> mySectionLists = sectionListService.getSectionLists(email);
            return ResponseEntity.ok(mySectionLists); 
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    
}
