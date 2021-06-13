package com.example.teams.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "team")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Team extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "team_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "commission")
	private Float commission;

}