package com.github.community.authserver.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* Utility class for doing basic operations
* related to security. A helper class.
* */
public class SecurityUtil {

    public static List<GrantedAuthority> toGrantedAuthority(String...roles) {
        return Arrays.asList(roles).stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
