package com.wordnet.community.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name="User", schema = "public")
@AllArgsConstructor
@Data
@SessionScope
@Component
public class User implements Serializable,UserDetails {

    private static final long serialVersionUID = 9062233004090752586L;

    public enum Authority {ROLE_USER, ROLE_ADMIN};

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
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

    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username", length = 100)
    private String username;

    @NotNull
    @Column(name = "password", length = 20)
    private String password;

    @NotNull
    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "sex", length = 1)
    private String sex;

    @Column(name = "age", length = 1)
    private Integer age;

    @Column(name = "avatar", length = 250)
    private String avatar;

    @Column(name = "nickname", length = 250)
    private String nickname;

    @Size(max =250)
    @Column(name = "introduction", length = 250)
    private String introduction;

    @CreatedDate
    @Column(name = "created")
    private Timestamp created;

    @LastModifiedDate
    @Column(name = "modified")
    private Timestamp modified;

    public User() {
    }

}
