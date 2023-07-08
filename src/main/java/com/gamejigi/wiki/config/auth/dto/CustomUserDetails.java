package com.gamejigi.wiki.config.auth.dto;


import com.gamejigi.wiki.domain.member.Member;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomUserDetails implements OAuth2User, UserDetails, Serializable {

    private final Member member;

    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(
            Member member,
            Map<String, Object> attributes,
            Collection<? extends GrantedAuthority> authorities) {
        //이 생성자는 소셜 로그인 할때 씀
        this.member = member;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public CustomUserDetails(
            Member member) {
        //이 생성자는 일반 로그인 할때 씀
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPw();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return member.getName();
    }
}
