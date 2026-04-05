package com.Mylist.demo.Item_List;

// Next : Validate Null / Empty Values in Request DTOs

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mylist.demo.Item_List.dto.ListRequest;
import com.Mylist.demo.Item_List.dto.UpdateList;
import com.Mylist.demo.Section_List.SectionList;
import com.Mylist.demo.Section_List.SectionListRepository;

@Service
public class ItenListService {
    @Autowired
    private ItemListRepository itemListRepository;

    @Autowired
    private SectionListRepository sectionListRepository;

    public ItemList createItemList(ListRequest request, Long SectionListId, String email) {
        SectionList sectionList = sectionListRepository.findById(SectionListId)
            .orElseThrow(() -> new RuntimeException("Section List Not Found"));

        if (!sectionList.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }
        ItemList itemList = new ItemList();
        itemList.setTitle(request.getTitle());
        itemList.setSectionList(sectionList);
        itemList.setIsDone(false);
        return itemListRepository.save(itemList);
    }

    public List<ItemList> getItemLists(Long SectionListId, String email) {
        SectionList sectionList = sectionListRepository.findById(SectionListId)
            .orElseThrow(() -> new RuntimeException("Section List Not Found"));

        if (!sectionList.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }
        return itemListRepository.findBySectionList(sectionList);
    }
    
    public ItemList updateItemList(Long itemListId, UpdateList request, String email) {
        ItemList updatedItemList = itemListRepository.findById(itemListId)
            .orElseThrow(() -> new RuntimeException("Item List Not Found"));

        if (!updatedItemList.getSectionList().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }
        
        updatedItemList.setTitle(request.getTitle());
        updatedItemList.setIsDone(request.getIsDone());
        return itemListRepository.save(updatedItemList);
    }

    public String deleteItemList(Long itemListId, String email) {
        ItemList deletedItemList = itemListRepository.findById(itemListId)
            .orElseThrow(() -> new RuntimeException("Item List Not Found"));

        if (!deletedItemList.getSectionList().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized Access");
        }
        itemListRepository.delete(deletedItemList);
        return "Item List " + deletedItemList.getTitle() + " Deleted Successfully";
    }
}
