package com.example.players.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "player")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Player extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "player_id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "team_id")
	private Integer teamId;
	@Column(name = "months_experience")
	private Integer monthsExperience;
	@Column(name = "age")
	private Integer age;
	
}