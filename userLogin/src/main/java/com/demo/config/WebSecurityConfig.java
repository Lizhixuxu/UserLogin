package com.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.demo.entity.User;
import com.demo.mapper.UserRepository;


/**
 * spring security 配置
 * @author Administrator
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserRepository userRepository;
	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<User> list = userRepository.findAll();
		for(User u:list) {
			auth.inMemoryAuthentication()
			.withUser(u.getUserName())
			.password(u.getPassword())
			.authorities("ADMIN");
		}
	
	}

	/**
	 * 请求授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().headers().disable()
		 .authorizeRequests()
		 .antMatchers("/").permitAll() // 配置不需要身份认证的请求地址
		 .anyRequest().authenticated() // 其他所有访问路径需要身份认证
		 .and()
		 .formLogin()
		 .loginPage("/login") // 指定登录请求地址
		 .defaultSuccessUrl("/admin") // 登录成功后的默认跳转页面
		 .permitAll()
		 .and()
		 .logout()
		 .logoutSuccessUrl("/login")
		 .permitAll();
	}

	
}
