package com.Mylist.demo.Item_List;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Mylist.demo.Section_List.SectionList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_list")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false, length = 100) 
    private String title;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private SectionList sectionList;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
