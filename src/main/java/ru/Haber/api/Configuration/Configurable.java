package ru.Haber.api.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;
import lombok.experimental.FieldDefaults;
import ru.Haber.api.Configuration.Annotations.ConfigurableValue;

@Getter
@FieldDefaults(level=AccessLevel.PRIVATE) 
@ToString 
@EqualsAndHashCode
@NoArgsConstructor
public class Configurable {
	
	String packageLink;
	
	ConfigurableValue configLink;
	
	static FileConfiguration configFile;
	
	@SuppressWarnings("unused")
	private static final List<Class<?>> instances = Lists.newLinkedList();
	
	@SuppressWarnings("unused")
	private static final Map<Class<?>, String> links = Maps.newConcurrentMap();
	
	private final Logger logger = Logger.getLogger("CubeStream");
	
	
	public static void setupConfig(@NotNull FileConfiguration config) {
		configFile = config;
	}
	
	
	public static Configurable configImpl(@NotNull Object instance) {

			for (Field field : instance.getClass().getDeclaredFields()) {
				if(field.isAnnotationPresent(ConfigurableValue.class)) {
					field.setAccessible(true);
					Annotation anno = field.getAnnotation(ConfigurableValue.class);
					try {
						val configurableValue = ((ConfigurableValue)anno);
						@NotNull val configLink = Optional.ofNullable(configurableValue.link())
								.orElseThrow(IllegalArgumentException::new);
						
						switch (configurableValue.type()) {
							case STRING:
								
								//field.set(new Object(), this.configFile.getString(configLink));
								FieldUtils.writeField(instance, 
										field.getName(), 
										configFile.getString(configLink).replace("&", "§"), true);
								break;
							case BOOLEAN:
								
								//field.set(new Object(), this.configFile.getBoolean(configLink, false));
								FieldUtils.writeField(instance, field.getName(),configFile.getBoolean(configLink), true);
								break;
							case INTEGER:
								
								//field.set(new Object(), this.configFile.getInt(configLink));
								FieldUtils.writeField(instance, field.getName(),configFile.getInt(configLink), true);
								break;
							case DOUBLE:
								
								//field.set(new Object(), this.configFile.getDouble(configLink, 0));
								FieldUtils.writeField(instance, field.getName(),configFile.getDouble(configLink), true);
								break;
							case LIST_STRING:
								FieldUtils.writeField(instance, field.getName(),configFile.getStringList(configLink).parallelStream().map(obj->obj.replace("&", "§")).collect(Collectors.toList()), true);
								break;
							case CONFIG:
								FieldUtils.writeField(instance, field.getName(),configFile, true);
								break;
						}
						
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						
					}
				}
			}
			return new Configurable();
		
	}
	

}
