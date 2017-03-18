package com.vektorel.oot.util;

import java.io.Serializable;

public class TestAyar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5153584791956733465L;
	String testAyar;

	public TestAyar(String testAyar) {
		this.testAyar = testAyar;
	}

	public String getTestAyar() {
		return testAyar;
	}

	public void setTestAyar(String testAyar) {
		this.testAyar = testAyar;
	}
}
