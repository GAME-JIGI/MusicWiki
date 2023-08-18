package com.gamejigi.wiki.config.auth.dto;


import com.gamejigi.wiki.domain.member.Member;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomUserDetails extends User implements OAuth2User, Serializable {

    private final Member member;

    private Map<String, Object> attributes;
    private final Collection<GrantedAuthority> authorities;

    public CustomUserDetails(
            Member member,
            Map<String, Object> attributes) {
        //이 생성자는 소셜 로그인 할때 씀
        super(member.getName(), member.getPw(), List.of(member.getRole().getGrantedAuthority()));
        this.member = member;
        this.attributes = attributes;
        this.authorities = List.of(member.getRole().getGrantedAuthority());
    }

    public CustomUserDetails(
            Member member) {
        //이 생성자는 일반 로그인 할때 씀
        super(member.getName(), member.getPw(), List.of(member.getRole().getGrantedAuthority()));
        this.member = member;
        this.authorities = List.of(member.getRole().getGrantedAuthority());
    }

    public Member getPrincipal() {
        return member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
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
    public String getName() {
        return member.getName();
    }
}
