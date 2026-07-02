package com.emranhss.SAAS.config;

import com.emranhss.SAAS.repository.TokenRepository;
import com.emranhss.SAAS.service.CustomUserDetailsService;
import com.emranhss.SAAS.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;

        // 🔹 1. Extract JWT from cookie
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        // 🔹 If no token → continue
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // 🔥 2. Validate token in DB (revoked + expired)
                var storedTokenOpt = tokenRepository.findAllByToken(jwt).stream().findFirst();

                if (storedTokenOpt.isEmpty()) {
                    filterChain.doFilter(request, response);
                    return;
                }

                var storedToken = storedTokenOpt.get();

                if (storedToken.isRevoked() || storedToken.isExpired()) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // 🔥 3. Validate JWT signature + expiration
                if (!jwtService.isTokenValid(jwt, userDetails)) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // 🔥 4. Set Authentication
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (Exception e) {
            logger.warn("JWT authentication failed: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }




//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        // 1️⃣ Extract JWT from HttpOnly cookie
//        String jwt = null;
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                if ("jwt".equals(cookie.getName())) {
//                    jwt = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        if (jwt == null) {
//            filterChain.doFilter(request, response); // no token, skip filter
//            return;
//        }
//
//        String userEmail;
//        try {
//            userEmail = jwtService.extractUsername(jwt); // extract email from token
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or expired token");
//            return;
//        }
//
//        // 2️⃣ Authenticate if not already
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//
//
//            boolean isTokenValidInDb = tokenRepository.findAllByToken(jwt)
//                    .stream()
//                    .anyMatch(token -> !token.isRevoked());
//
//            if (!jwtService.isTokenValid(jwt, userDetails) || !isTokenValidInDb) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid or revoked token");
//                return;
//            }
//
//            // 3️⃣ Set authentication in security context
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
//
//        // 4️⃣ Continue filter chain
//        filterChain.doFilter(request, response);
//    }
}