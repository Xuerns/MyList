package com.Mylist.demo.Section_List;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Mylist.demo.Section_List.dto.SectionRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/sections")
@CrossOrigin(origins = "*")
public class SectionListController {
    @Autowired
    private SectionListService sectionListService;

    @PostMapping
    public ResponseEntity<?> createSectionList(@RequestParam("title") String title, @RequestParam(value = "image", required = false) MultipartFile image, Principal principal) {
        String email = principal.getName();
        try {
            SectionRequest request = new SectionRequest();
            request.setTitle(title);
            SectionList newSectionList = sectionListService.createSectionList(request, image, email);
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
    
    @PutMapping("/{sectionListId}")
    public ResponseEntity<?> updateSectionList(@PathVariable Long sectionListId, @RequestBody SectionRequest request, Principal  principal) {
        String email = principal.getName();
        try {
            SectionList updatedSectionList = sectionListService.updateSectionList(sectionListId, request, email);
            return ResponseEntity.ok(updatedSectionList);
        
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    
    @DeleteMapping("/{sectionListId}")
    public ResponseEntity<?> deleteSectionList(@PathVariable Long sectionListId, Principal principal) {
        String email = principal.getName();
        try {
            String responseMessage = sectionListService.deleteSectonList(sectionListId, email);
            return ResponseEntity.ok(responseMessage);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
