package hu.me.fdsz.model;

import hu.me.fdsz.model.Util.HasImage;
import hu.me.fdsz.model.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@ToString(of = "username")
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends Person implements UserDetails, HasImage {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "image", referencedColumnName = "id")
    private Image image;

    @OneToMany
    @JoinColumn(name = "author", referencedColumnName = "id")
    private List<FeedPost> feedPostList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Optional<Image> getImage() {
        return Optional.ofNullable(image);
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

}
