package com.bicimapa.prestamos.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

	private String name;
	private String phone;
	
	private List<Badge> badges;
}
