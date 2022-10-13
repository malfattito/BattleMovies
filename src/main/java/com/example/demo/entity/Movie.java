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
@Table(name = "movie_table")
public class Movie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String urlImage;
	
	@NonNull
	private double rating;

	public Movie(@NonNull String name, @NonNull String urlImage, @NonNull double rating) {
		this.name = name;
		this.urlImage = urlImage;
		this.rating = rating;
	}
}
