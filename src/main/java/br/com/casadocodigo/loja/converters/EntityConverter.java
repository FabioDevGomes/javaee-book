package br.com.casadocodigo.loja.converters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Id;

import org.picketbox.util.StringUtil;

@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		if (StringUtil.isNullOrEmpty(value)) {
			return null;
		}
		UISelectItems uiSelectItems = (UISelectItems) componet.getChildren().get(0);
		Collection<?> objects = (Collection<?>) uiSelectItems.getValue();
		Object foundEntity = objects.stream().filter((entity) -> {
			return getAsString(context, componet, entity).equals(value);
		}).findFirst().get();

		return foundEntity;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object value) {
		Field field = findField(value);
		return getIdField(value, field);
	}
	
	private String getIdField(Object value, Field idField){
		try {
			Field field = value.getClass().getDeclaredField(idField.getName());
			field.setAccessible(true);
			return field.get(value).toString();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	private Field findField(Object value) {
		Field idField = Arrays.stream(value.getClass().getDeclaredFields())
				.filter((field) -> field.getAnnotation(Id.class) != null).findFirst()
				.get();
		return idField;
	}

}
