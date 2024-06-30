package com.example.habit_tracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  
  @PostMapping
  public Habit createHabit(@RequestBody Habit habit){
    return habitService.createHabit(habit);
  }

  @PutMapping("{id}")
  public ResponseEntity<Habit> updateHabit(@PathVariable String id, @RequestBody Habit habitDetails){
    Optional<Habit> habitOptional = habitService.getHabitById(id);
    if(habitOptional.isPresent()){
      Habit habit = habitOptional.get();
      habit.setName(habitDetails.getName());
      habit.setDescription(habitDetails.getDescription());
      habit.setCompleted(habitDetails.isCompleted());
      return ResponseEntity.ok(habitService.updateHabit(id,habit));
        } else {
            return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable String id){
    Optional<Habit> habitOptional = habitService.getHabitById(id);
    if(habitOptional.isPresent()){
      habitService.deleteHabit(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
