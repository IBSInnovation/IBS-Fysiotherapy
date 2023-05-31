package org.ibs.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Data
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "roleId")
    private Long id;
    @Column(name="roleName")
    private String Name;

    public Role() {
    }
}
