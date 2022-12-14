package com.base.security;

import java.security.Principal;
import com.base.vo.UserVo;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * AuthSecurityUtil.
 *
 * @author components on 2022/09/26
 * @version 1.0
 * @since 1.0.0
 */
public final class AuthSecurityUtil {

    /**
     * Get logged in user keycloak.
     *
     * @return UserVo
     * @author components on 2022/09/26
     */
    public static UserVo getUserLogin() {
        KeycloakAuthenticationToken authentication =
            (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        return getUser(principal);
    }

    /**
     * Get logged in user keycloak.
     *
     * @param principal Principal
     * @return UserVo
     * @author components on 2022/09/26
     */
    public static UserVo getUser(Principal principal) {
        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
            AccessToken accessToken = kPrincipal.getKeycloakSecurityContext().getToken();
            return UserVo.builder().userId(accessToken.getId()).id(accessToken.getId()).firstName(accessToken.getGivenName())
                .lastName(accessToken.getFamilyName()).email(accessToken.getEmail()).build();
        }
        return null;
    }

    /**
     * Constructor.
     */
    private AuthSecurityUtil() {
    }

}