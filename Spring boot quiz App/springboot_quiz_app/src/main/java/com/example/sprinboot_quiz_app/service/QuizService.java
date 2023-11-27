package com.example.sprinboot_quiz_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sprinboot_quiz_app.Dao.QuestionDao;
import com.example.sprinboot_quiz_app.Dao.QuizDao;
import com.example.sprinboot_quiz_app.model.QuestionWrapper;
import com.example.sprinboot_quiz_app.model.Quiz;
import com.example.sprinboot_quiz_app.model.Response;
import com.example.sprinboot_quiz_app.model.questions;

@Service
public class QuizService 
{
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) 
    {
        List<questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestions(questions);
       quizDao.save(quiz);
       return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) 
    {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<questions> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(questions q : questionsfromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);       
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get(); 
        List<questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response:responses)
        {
            if(response.getResponse().equals(questions.get(i).getRight_answer()))
                right++;
                i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
	}    

}
