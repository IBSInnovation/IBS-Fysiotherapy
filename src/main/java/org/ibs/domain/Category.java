package org.ibs.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*@Getter*/
/*@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component*/
@Data
@NoArgsConstructor
@Component
@Entity
    public class Category {
    @Id
    @GeneratedValue
    @Column(name = "CategoryId")
    private Long id;
    @Column(name = "categoryName")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exerciseId")
    private List<Exercise> exercises = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }

    public void addExercise(Exercise exercise) {
        if (!exercises.contains(exercise)) exercises.add(exercise);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(exercises, category.exercises);
    }
}
