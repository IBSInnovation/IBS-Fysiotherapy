package org.ibs.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Component
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;


    public List<String> getRoles(){
        return List.of();
    }
    public List<SimpleGrantedAuthority> getAuthorities() {
        return getRoles().stream().map(item->new SimpleGrantedAuthority("ROLE_"+item)).collect(Collectors.toList());
    }


}
