package mova.laboratorio.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
		// Los recursos estaticos no requieren autorizacion
			.antMatchers("/bootstrap/**",
					     "/images/**",
					     "/tinymce/**",
					     "/logos/**").permitAll() 
			
			// Paginas publicas sin necesidad de autentificacion
			.antMatchers("/",
					     "/singup",
					     "/login",
					     "/usuarios/save",
					     "/bcrypt/**",
					     "/search",
					     "/vacantes/view/**").permitAll()
			
			// Roles requeridos para las URLs correspondientes
			.antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
			.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
			.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
			.antMatchers("/solicitudes/index").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
			.antMatchers("/solicitudes/save").hasAnyAuthority("USUARIO","SUPERVISOR","ADMINISTRADOR")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll();
	}

}
