package com.example.transfers.entity.external;


import com.example.transfers.entity.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Team extends Auditable {
	private Integer id;
	private String name;
	private Float commission;
}
