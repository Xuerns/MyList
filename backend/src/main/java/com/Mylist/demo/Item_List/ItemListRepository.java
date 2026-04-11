package com.Mylist.demo.Item_List;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mylist.demo.Section_List.SectionList;
import com.Mylist.demo.Users.Users;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Long> {
    List<ItemList> findBySectionList(SectionList sectionList);
    List<ItemList> findBySectionList_UserAndIsDoneTrue(Users user);
}
