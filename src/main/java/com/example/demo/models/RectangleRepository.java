package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle,Integer> {
    List<Rectangle> findByName(String name);
    List<Rectangle> findByWidth(int width);
    Rectangle findByUid(int uid);
}
