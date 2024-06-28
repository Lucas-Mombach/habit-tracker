package com.example.habit_tracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "habits")
public class Habit {
  @Id
  private String id;
  private String name;
  private String description;
  private boolean completed;
}
