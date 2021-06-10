package com.examportalserve.controller;

import com.examportalserve.model.exam.Question;
import com.examportalserve.model.exam.Quiz;
import com.examportalserve.services.QuestionService;
import com.examportalserve.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get all question of qid

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionByQid(@PathVariable("qid") Long qid){
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(quiz));

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumOfQuestion())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumOfQuestion() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionByQidAdmin(@PathVariable("qid") Long qid){
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(quiz));
    }

    // get single question
    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    // delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }
}
