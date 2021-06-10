package com.examportalserve.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qid;
    private String title;
    private String description;
    private String maxMarks;
    private String numOfQuestion;
    private boolean active= false;

    //relationship
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    public Quiz() {
    }

    public Long getQid() {
        return qid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(String numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
