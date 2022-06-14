package com.proyecto;

import java.util.ArrayList;
import java.util.Arrays;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Aspect
@Component
public class AuthorizedExecution {

	@Autowired
	private Session sesion;
	
	@Around("@annotation(authorized)")
	public Object checkRol(ProceedingJoinPoint proceedingJoinPoint, Authorized authorized) throws Throwable {
		
		ArrayList<String> rolList = new ArrayList(Arrays.asList(authorized.roles()));
		
		Object result = "redirect:/Home";
		
		if (rolList.contains(sesion.get(Session.TIPO_ROL))) {
			try {
				result = (Object) proceedingJoinPoint.proceed();
			} catch (Throwable e) {
				System.out.println("Error al continuar con la ejecución normal del programa pero los Roles son correctos." + e.getMessage());
			}
		} 
		
		
		return result;
	}
	
}
