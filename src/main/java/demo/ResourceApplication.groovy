package demo

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
class ResourceApplication  extends WebSecurityConfigurerAdapter {

  @RequestMapping('/')
  def home() {
    [id: UUID.randomUUID().toString(), content: 'Hello World']
  }

  static void main(String[] args) {
    SpringApplication.run ResourceApplication, args
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
    http.authorizeRequests().anyRequest().authenticated()
  }

  
}