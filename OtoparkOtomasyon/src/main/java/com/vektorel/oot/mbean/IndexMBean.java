package com.vektorel.oot.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="indexBean")
@ViewScoped
public class IndexMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330075364711917067L;

	
	@PostConstruct
	private void init() {
		System.out.println("Nesne Oluþtu");
	}
	
	
}


