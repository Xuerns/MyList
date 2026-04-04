package com.Mylist.demo.Section_List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mylist.demo.Users.Users;

@Repository
public interface SectionListRepository extends JpaRepository<SectionList, Long>  {
    List<SectionList> findByUser(Users user);
}
