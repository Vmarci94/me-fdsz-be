package hu.me.fdsz.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, CLIENT;

  public String getAuthority() {
    return name();
  }

}
