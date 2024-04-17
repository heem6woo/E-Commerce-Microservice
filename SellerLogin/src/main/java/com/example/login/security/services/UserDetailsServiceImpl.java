package com.example.login.security.services;

import com.example.login.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.models.User;
import com.example.login.repository.UserRepository;

import java.util.concurrent.TimeUnit;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  JwtUtils jwtUtils;
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  @jakarta.transaction.Transactional
  public void logout(String accessToken){


    String UserName = jwtUtils.getUserNameFromJwtToken(accessToken);
    UserDetailsImpl userDetails = (UserDetailsImpl) loadUserByUsername(UserName);
    //엑세스 토큰 남은 유효시간
    Long expiration = jwtUtils.getExpiration(accessToken);

    //Redis Cache에 저장
    redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

    //리프레쉬 토큰 삭제
    refreshTokenRepository.delete(userDetails.getId());
  }

}
