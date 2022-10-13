package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user_table")
public class User implements Comparable<User>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String login;

    @NonNull
    private String senha;

    @NonNull
    private Long points;

    @Override
    public int compareTo(User u) {
        return this.points.compareTo(u.points);
    }
    
    public User(@NonNull String login, @NonNull String senha, @NonNull Long points) {
        this.login = login;
        this.senha = senha;
        this.points = points;
    }
}
