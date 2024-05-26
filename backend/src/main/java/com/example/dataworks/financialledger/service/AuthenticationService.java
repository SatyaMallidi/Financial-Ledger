package com.example.dataworks.financialledger.service;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.AuthenticationResponse;
import com.example.dataworks.financialledger.entity.Token;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.TokenRepository;
import com.example.dataworks.financialledger.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final TokenRepository tokenRepository;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserRepository repository,PasswordEncoder passwordEncoder,JwtService jwtService,
      TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.tokenRepository = tokenRepository;
    this.authenticationManager = authenticationManager;
  }


  public AuthenticationResponse register(User request) {
    if (repository.findByUsername(request.getUsername()) != null) {
      return new AuthenticationResponse(null,null,"User already exists");
    }
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());
    user.setEmail(request.getEmail());
    user = repository.save(user);

    String accessToken = jwtService.generateAccessToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(accessToken,refreshToken,user);

    return new AuthenticationResponse(accessToken,refreshToken,"User registration was successful");

  }

  public AuthenticationResponse authenticate(User request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));

    User user = repository.findByUsername(request.getUsername());
    if (user == null) {
      throw new UserExceptionNotFound("The user is not found");
    }

    String accessToken = jwtService.generateAccessToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    revokeAllTokenByUser(user);
    saveUserToken(accessToken,refreshToken,user);

    return new AuthenticationResponse(accessToken,refreshToken,"User login was successful");

  }

  private void revokeAllTokenByUser(User user) {
    List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getUserId());
    if (validTokens.isEmpty()) {
      return;
    }
    validTokens.forEach(t -> {t.setLoggedOut(true);});
    tokenRepository.saveAll(validTokens);
  }

  private void saveUserToken(String accessToken,String refreshToken,User user) {
    Token token = new Token();
    token.setAccessToken(accessToken);
    token.setRefreshToken(refreshToken);
    token.setLoggedOut(false);
    token.setUser(user);
    tokenRepository.save(token);
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        throw new IllegalArgumentException("Authorization header is missing or invalid");
    }

    String token = authHeader.substring(7);

    String username = jwtService.extractUsername(token);

    User user = repository.findByUsername(username);
    if (user == null) {
        throw new UserExceptionNotFound("The user is not found");
    }

    if(jwtService.isValidRefreshToken(token, user)) {
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);
    } else {
        throw new IllegalArgumentException("Invalid or expired refresh token");
    }
}

}
