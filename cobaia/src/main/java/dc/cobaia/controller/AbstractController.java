package dc.cobaia.controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

public abstract class AbstractController {
	
	private Map model = new HashMap();
	
	protected void add(String key, Object value) {
		model.put(key, value);
	}
	
	protected ModelAndView view(String viewName) {
		return new ModelAndView(model, viewName);
	}
	
	protected ModelAndView view(String viewName, String key, Object value) {
		add(key, value);
		return view(viewName);
	}

}
