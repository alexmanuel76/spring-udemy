package mova.laboratorio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	
	@Value("${empleosapp.ruta.imagenes}")
	private String rutaImagenes;
	
	@Value("${empleosapp.ruta.cv}")
	private String rutaCv;
	
	public void addResourseHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/logos/**")
		        .addResourceLocations("file:"+rutaImagenes);
		
		registry.addResourceHandler("/cv/**")
		        .addResourceLocations("file:"+rutaCv);
	}

}
