package com.example.sprinboot_quiz_app.controller;

import java.util.List;

// import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprinboot_quiz_app.model.questions;
import com.example.sprinboot_quiz_app.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController 
{
    @Autowired
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) 
    {
        this.questionService = questionService;
    }
    
    @GetMapping("/allquestions")
    public ResponseEntity< List<questions>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<questions>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);     
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody questions question)
    {
        return questionService.addQuestion(question);
    }
}
