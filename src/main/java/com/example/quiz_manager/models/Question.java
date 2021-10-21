package com.example.quiz_manager.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "display_order")
    private Long displayOrder;

    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question(String text, Long displayOrder) {
        this.text = text;
        this.displayOrder = displayOrder;
    }

    public Question() {
    }
}
