package com.example.habit_tracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.habit_tracker.model.Habit;

@Repository
public interface HabitRepository extends MongoRepository<Habit, String> {}
