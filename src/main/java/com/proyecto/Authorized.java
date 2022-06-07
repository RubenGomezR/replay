package com.proyecto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized {
	
	static final String ADMIN = "Admin";
	static final String REGISTRADO = "Registrado";
	static final String ANONIMO = "anonimo";
	
	public String[] roles() default "";

}