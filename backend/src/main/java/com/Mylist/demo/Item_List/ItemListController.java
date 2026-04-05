package com.Mylist.demo.Item_List;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Mylist.demo.Item_List.dto.ListRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemListController {
    @Autowired
    private ItenListService itemListService;

    @PostMapping
    public ResponseEntity<?> createItemList(@RequestBody ListRequest listRequest, Long sectionListId, Principal principal) {
        String email = principal.getName();
        try {
            ItemList itemList = itemListService.createItemList(listRequest, sectionListId, email);
            return ResponseEntity.ok(itemList);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getItemList(Principal principal, Long sectionListId) {
        String email = principal.getName();
        try {
            List<ItemList> itemLists = itemListService.getItemLists(sectionListId, email);
            return ResponseEntity.ok(itemLists);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    } 
  
}
