package com.stc.filemngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.filemngt.entity.Item;

@Repository
public interface ItemReopsitory extends JpaRepository<Item, Long> {

}
