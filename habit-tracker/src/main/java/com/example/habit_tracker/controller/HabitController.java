package com.example.habit_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.service.HabitService;

@RestController
@RequestMapping("/api/v1/habits")
public class HabitController {
  @Autowired
  private HabitService habitService;

  @GetMapping
  public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Habit> getHabitById(@PathVariable String id) {
    return habitService.getHabitById(id)
    .map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
  }

}
