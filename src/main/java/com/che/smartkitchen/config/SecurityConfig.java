package com.che.smartkitchen.config;

import com.che.smartkitchen.exception.RestAuthenticationEntryPoint;
import com.che.smartkitchen.filter.JwtAuthorizationFilter;
import com.che.smartkitchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "cheMusic";
    public static final long EXPIRATION_TIME = 864000000;//10days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/";
    public static final String TOKEN_URL = "/tokens";
    UserService userService;

    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    //  定义需要拦截的URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("5");
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, TOKEN_URL).permitAll()
                .antMatchers(HttpMethod.DELETE, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        System.out.println("6");

    }

    //配置拦截资源，例如过滤掉css/js/images等静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/swagger**/**")
                .antMatchers("webjars/**")
                .antMatchers("/v3/**")
                .antMatchers("/doc.html");
    }

    //配置系统中的用户，就相当于这些用户已经完成注册了
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

}
