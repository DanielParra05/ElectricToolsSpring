package com.spring.electric.tools.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageItem {
	private int number;
	private boolean current;
	
	public PageItem (int numero, boolean actual) {
		this.number = numero;
		this.current = actual;		
	}
	
	
}
