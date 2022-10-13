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
@Table(name = "battle_table")
public class Battle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Boolean result;
	
	@NonNull
	@ManyToOne
	private User user;
	
	@NonNull
	@ManyToOne
	private Movie movie1;
	
	@NonNull
	@ManyToOne
	private Movie movie2;

	@NonNull
	private Integer match;

	public Battle( @NonNull User user, @NonNull Movie movie1, @NonNull Movie movie2, @NonNull Integer match) {
		this.user = user;
		this.movie1 = movie1;
		this.movie2 = movie2;
		this.match = match;
	}
}