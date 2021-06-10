package com.examportalserve.services;

import com.examportalserve.model.exam.Question;
import com.examportalserve.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionOfQuiz(Quiz quiz);
    public void deleteQuestion(Long quesId);
}
