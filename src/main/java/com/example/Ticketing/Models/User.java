package com.example.Ticketing.Models;

import com.example.Ticketing.Role.UserRole;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;
import java.util.Collections;

@Data

@Document (collection = "Users")

@EqualsAndHashCode

@Getter

@Setter

@ToString

@NoArgsConstructor


public class User extends AbstractEntity implements UserDetails {

    @Transient
    public static final String SEQUENCE_NAME = "User_sequence";

    private String firstname;

    private String lastname;

    @Indexed(unique = true)
    private String email;

    private String phone_number;

    @Indexed(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Boolean locked;

    private Boolean enabled;

    public User(String firstname,
                String lastname,
                String email,
                String phone_number,
                String username,
                String password,
                UserRole role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setUsername(String firstname, String lastname) {
        this.username = firstname + lastname;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
