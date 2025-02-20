package org.example.itkissues.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CustomGitHubOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    Logger log = LogManager.getLogger(CustomGitHubOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate
                = new org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String githubLogin = oAuth2User.getAttribute("login");
        String email = oAuth2User.getAttribute("email");
        if(email == null) email = "not public";

        Set<GrantedAuthority> mappedAuthorities = new HashSet<>(oAuth2User.getAuthorities());

        if ("grisellaAlaina".equalsIgnoreCase(githubLogin)
                || "serggood@gmail.com".equalsIgnoreCase(email)) {
            log.info("Успешная аутентификация пользователя: {}", githubLogin);
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("email", email);

        return new DefaultOAuth2User(
                mappedAuthorities,
                attributes,
                "login"
        );
    }
}
