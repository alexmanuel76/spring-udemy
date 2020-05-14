package mova.laboratorio.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, estatus from Usuarios where username=?")
				.authoritiesByUsernameQuery("SELECT u.username, p.perfil FROM UsuarioPerfil up " + 
						                    "INNER JOIN Usuarios u ON u.id = up.idUsuario " + 
						                    "INNER JOIN Perfiles p ON p.id = up.idPerfil " + 
						                    "WHERE u.username = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
			.antMatchers("/bootstrap/**",
					     "/images/**",
					     "/tinymce/**",
					     "/logos/**").permitAll() 
			.antMatchers("/",
					     "/register",
					     "/search",
					     "/vacantes/view/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll();
	}

}
