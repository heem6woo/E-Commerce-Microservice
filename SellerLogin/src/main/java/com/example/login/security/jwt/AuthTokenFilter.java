  package com.example.login.security.jwt;

  import java.io.IOException;
  import java.util.Objects;

  import com.example.login.controllers.AuthController;
  import com.example.login.security.services.UserDetailsImpl;
  import io.jsonwebtoken.JwtException;
  import jakarta.servlet.FilterChain;
  import jakarta.servlet.ServletException;
  import jakarta.servlet.http.HttpServletRequest;
  import jakarta.servlet.http.HttpServletResponse;

  import org.apache.catalina.core.ApplicationContext;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
  import org.springframework.security.core.context.SecurityContextHolder;
  import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
  import org.springframework.web.filter.OncePerRequestFilter;

  import com.example.login.security.services.UserDetailsServiceImpl;

  import javax.security.auth.Subject;

  public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    AuthController reissueController;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
      try {
        System.out.println(request.getHeader("Authorization"));
        String jwt = request.getHeader("Authorization");
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
          String username = jwtUtils.getUserNameFromJwtToken(jwt);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          String requestURI = request.getRequestURI();
          if(jwtUtils.getTokenType(jwt).equals("rtk") && !requestURI.equals("/api/auth/refreshToken")) {
            System.out.println("잘못된 토큰");
            throw new JwtException("토큰을 확인하세요.");
          }
          UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(userDetails,
                          null,
                          userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (JwtException e) {
        logger.error("Cannot set user authentication: {}", e);
      }

      filterChain.doFilter(request, response);
    }




  }