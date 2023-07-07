package com.gamejigi.wiki.config.auth.dto;


import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.domain.member.role.Role;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Attributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;

    @Builder
    public Attributes(
            Map<String, Object> attributes,
            String nameAttributeKey,
            String name,
            String email,
            String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static Attributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    //소셜 로그인(구글) 쓴다면 사용
    private static Attributes ofGoogle(
            String userNameAttributeName,
            Map<String, Object> attributes) {
        return Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
