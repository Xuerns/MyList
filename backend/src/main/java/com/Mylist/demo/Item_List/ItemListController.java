package com.Mylist.demo.Item_List;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mylist.demo.Item_List.dto.ListRequest;
import com.Mylist.demo.Item_List.dto.UpdateList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemListController {
    @Autowired
    private ItenListService itemListService;

    @PostMapping("section/{sectionListId}")
    public ResponseEntity<?> createItemList(@PathVariable Long sectionListId,@RequestBody ListRequest listRequest, Principal principal) {
        String email = principal.getName();
        try {
            ItemList itemList = itemListService.createItemList(listRequest, sectionListId, email);
            return ResponseEntity.ok(itemList);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping("section/{sectionListId}")
    public ResponseEntity<?> getItemList(@PathVariable Long sectionListId, Principal principal) {
        String email = principal.getName();
        try {
            List<ItemList> itemLists = itemListService.getItemLists(sectionListId, email);
            return ResponseEntity.ok(itemLists);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    } 

    @GetMapping("completed")
    public ResponseEntity<?> getCompletedItems(Principal principal) {
        String email = principal.getName();
        try {
            List<ItemList> completedItems = itemListService.getCompletedItems(email);
            return ResponseEntity.ok(completedItems);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
  
    @PutMapping("/{itemListId}")
    public ResponseEntity<?> updateItemList(@PathVariable Long itemListId, @RequestBody UpdateList request, Principal principal) {
        String email = principal.getName();
        try {
            ItemList updatedItemList = itemListService.updateItemList(itemListId, request, email);
            return ResponseEntity.ok(updatedItemList);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping("/{itemListId}")
    public ResponseEntity<?> deleteItemList(@PathVariable Long itemListId, Principal principal) {
        String email = principal.getName();
        try {
            String result = itemListService.deleteItemList(itemListId, email);
            return ResponseEntity.ok(result);
        } catch (RuntimeException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
