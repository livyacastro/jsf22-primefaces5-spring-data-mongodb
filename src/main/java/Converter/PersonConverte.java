package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Person;

import com.google.gson.Gson;

@FacesConverter(value = "autocompleteConverter", forClass = Person.class)
public class PersonConverte implements Converter {

	private final String ATTRIBUTE_CLASS = "AutocompleteConverter.class";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || "null".equals(value) || value.trim().equals("") || value.trim().isEmpty())
			return null;
		return new Gson().fromJson(value, (Class<?>) component.getAttributes().get(ATTRIBUTE_CLASS));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		component.getAttributes().put(ATTRIBUTE_CLASS, value.getClass());
		return new Gson().toJson(value);
	}

}
