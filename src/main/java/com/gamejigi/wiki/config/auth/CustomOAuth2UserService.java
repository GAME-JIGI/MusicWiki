package com.gamejigi.wiki.config.auth;

import com.gamejigi.wiki.config.auth.dto.Attributes;
import com.gamejigi.wiki.config.auth.dto.CustomUserDetails;
import com.gamejigi.wiki.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //소셜 로그인 할때 실행되는 코드
        //CustomUserService 와 비슷한 역할

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        Attributes attributes = Attributes.
                of(registrationId, userNameAttributeName,
                        oAuth2User.getAttributes());

        Member member = getInformation(attributes);

        return new CustomUserDetails(
                member,
                attributes.getAttributes());
    }

    private Member getInformation(Attributes attributes) {
        Member member = Member.builder()
                .email(attributes.getEmail())
                .name(attributes.getName())
                .build();

        //소셜 로그인 되고 나서 실행되는 곳
        //로그인된 사용자 정보를 세션이나 DB에 저장하는 코드 필요

        return member;
    }
}

