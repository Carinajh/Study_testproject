package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ListenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListenerRepository extends JpaRepository<ListenerEntity,Long> {

}
