package com.macro.vsearch.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String passWord;
}