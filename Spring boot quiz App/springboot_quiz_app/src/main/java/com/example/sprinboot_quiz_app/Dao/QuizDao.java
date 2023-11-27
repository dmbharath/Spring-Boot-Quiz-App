package com.example.sprinboot_quiz_app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sprinboot_quiz_app.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
