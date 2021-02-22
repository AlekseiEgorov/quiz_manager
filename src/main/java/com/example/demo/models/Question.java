package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "text",
            nullable = false,
            columnDefinition = "text",
            unique = true
    )
    private String text;

    @Column(
            name = "display_order",
            nullable = false,
            columnDefinition = "bigint",
            unique = true
    )
    private Long displayOrder;

    @ManyToOne()
    @JoinColumn(
            name = "quiz_id", //Название отображаемое в базе данных
            nullable = false,
            referencedColumnName = "id", //Название первичного ключа другой таблицы.
            foreignKey = @ForeignKey(
                    name = "quiz_question_fk")) // Название внешнего ключа
    private Quiz quiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
