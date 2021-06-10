package com.examportalserve.controller;

import com.examportalserve.model.exam.Quiz;
import com.examportalserve.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    // add quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    // update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // get quizzes
    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get quiz
    @GetMapping("/{qid}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("qid") Long qid){
        return ResponseEntity.ok(this.quizService.getQuiz(qid));
    }

    //delete quiz
    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid){
        this.quizService.deleteQuiz(qid);
    }
}
