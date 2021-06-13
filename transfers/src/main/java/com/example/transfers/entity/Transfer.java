package com.example.transfers.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transfer")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Transfer extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "transfer_id")
	private Integer id;
	@Column(name = "to_team_id")
	private Integer toTeamId;
	@Column(name = "from_team_id")
	private Integer fromTeamId;
	@Column(name = "player_id")
	private Integer playerId;
	@Column(name = "transfer_fee")
	private Float transferFee;
}
