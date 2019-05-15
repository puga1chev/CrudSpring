package ru.puga1chev.crudspring.handler;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UrlAuthenticationSuccessHandler
            implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
                throws IOException {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/users");
            } else if (roles.contains("ROLE_USER")) {
                response.sendRedirect("/user");
            }
        }
}
