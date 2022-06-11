package ru.Haber.api.Configuration.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurableValue {

	String link() default "";
	
	ElementType type() default ElementType.CONFIG;
	
	enum ElementType {
		
		STRING,
		LIST_STRING,
		DOUBLE,
		BOOLEAN,
		INTEGER,
		CONFIG
		
	}

}
