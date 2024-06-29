package com.example.habit_tracker.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.repository.HabitRepository;
@Service
public class HabitService {
  @Autowired
  private HabitRepository habitRepository;
  
  public List<Habit> getAllHabits(){
    return habitRepository.findAll();
  }

  public Optional<Habit> getHabitById(String id){
    return habitRepository.findById(id);
  }

  public Habit createHabit(Habit habit){
    return habitRepository.save(habit);
  }

  public void deleteHabit(String id){
    Habit habit = habitRepository.findById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
    habitRepository.delete(habit);
  }

  public Habit updateHabit(String id, Habit habitDetails){
    Habit habit =  habitRepository.findById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
    
    habit.setName(habitDetails.getName());
    habit.setDescription(habitDetails.getDescription());
    habit.setCompleted(habitDetails.isCompleted());
    
    return habitRepository.save(habit);
  }

  
}
