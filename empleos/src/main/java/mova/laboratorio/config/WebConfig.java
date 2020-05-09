package mova.laboratorio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Value("${empleosapp.ruta.imagenes}")
	private String rutaImagenes;
	
	public void addResourseHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:d:/empleos/img-vacantes/");
		registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes);
		super.addResourceHandlers(registry);
	}

}
