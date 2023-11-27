package com.example.sprinboot_quiz_app.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprinboot_quiz_app.Dao.QuestionDao;
import com.example.sprinboot_quiz_app.model.questions;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    // @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<questions>> getAllQuestions(){
        try{
            List<questions> allQuestions = questionDao.findAll();
            return new ResponseEntity<>(allQuestions, HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity< List<questions>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(questions question) 
    {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }
}