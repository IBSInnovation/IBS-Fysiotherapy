package org.ibs.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "category")
    private List<Exercise> exercises = new ArrayList<>();

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
