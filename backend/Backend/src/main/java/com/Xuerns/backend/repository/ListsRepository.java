package com.Xuerns.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Xuerns.backend.entity.Lists;
import com.Xuerns.backend.entity.Users;

public interface ListsRepository extends JpaRepository<Lists, Long>{
    List<Lists> findByuserId(Long userId);

    List<Lists> findByUser(Users user);
}
