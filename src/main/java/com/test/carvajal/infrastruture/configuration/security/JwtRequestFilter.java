package com.test.carvajal.infrastruture.configuration.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null) {
            String username = this.getUsernameOfToken(tokenHeader);
            String token = this.getToken(tokenHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);
                if (this.validToken(token, userDetails)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            this.getUsernamePasswordAuthenticationToken(userDetails, request));
                    log.info("Usuario validado y autenticado exitosamente");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getUsernameOfToken(String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            try {
                return jwtUtil.getUsernameFromToken(this.getToken(tokenHeader));
            } catch (Exception e) {
                log.error("Error::. Token invalido");
            }
        }
        log.info("JWT TOKEN no inicia con Bearer String");
        return null;
    }

    private String getToken(String tokenHeader) {
        return tokenHeader.substring(7);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(UserDetails userDetails,
                                                                                       HttpServletRequest request) {
        UsernamePasswordAuthenticationToken userAuthenticate = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        userAuthenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userAuthenticate;
    }

    private boolean validToken(String token, UserDetails userDetails) {
        try {
            return this.jwtUtil.validateToken(token, userDetails);
        } catch (Exception e) {
            log.error("Error validando token");
            return false;
        }
    }

}
