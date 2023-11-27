package com.example.sprinboot_quiz_app.Dao;

import org.springframework.stereotype.Repository;


import com.example.sprinboot_quiz_app.model.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<questions, Integer>
{
     List<questions> findByCategory(String category);

     @Query(value = "SELECT * FROM questions q WHERE q.category= 'category ORDER BY RANDOM() LIMIT :numQ'", nativeQuery = true)
    List<questions> findRandomQuestionsByCategory(String category, int numQ);
}
