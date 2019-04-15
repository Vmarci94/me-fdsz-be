package hu.me.fdsz.security;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private JwtTokenProvider JwtTokenProvider;

  public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProviderImpl) {
    this.JwtTokenProvider = jwtTokenProviderImpl;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtTokenFilter customFilter = new JwtTokenFilter(JwtTokenProvider);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
