package com.job.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.dto.auth.AuthenticationRequestDto;
import com.job.common.dto.auth.AuthenticationResponseDto;
import com.job.common.dto.auth.RegisterRequestDto;
import com.job.common.entity.auth.Token;
import com.job.common.entity.auth.User;
import com.job.common.enums.Role;
import com.job.common.repository.auth.TokenRepository;
import com.job.common.repository.auth.UserRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.CoyoteOutputStream;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationUtil.class})
@ExtendWith(SpringExtension.class)
class AuthenticationUtilTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegister() {
        when(userRepository.save(Mockito.any())).thenReturn(new User());
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");
        AuthenticationResponseDto actualRegisterResult = authenticationUtil
                .register(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.ADMIN));
        assertEquals("ABC123", actualRegisterResult.getAccessToken());
        assertEquals("ABC123", actualRegisterResult.getRefreshToken());
        verify(userRepository).save(Mockito.any());
        verify(tokenRepository).save(Mockito.any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    @Test
    void testAuthenticate() throws AuthenticationException {
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponseDto actualAuthenticateResult = authenticationUtil
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        verify(userRepository).findByEmail(Mockito.any());
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(authenticationManager).authenticate(Mockito.any());
    }

    @Test
    void testAuthenticate2() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponseDto actualAuthenticateResult = authenticationUtil
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        verify(userRepository).findByEmail(Mockito.any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(authenticationManager).authenticate(Mockito.any());
    }

    @Test
    void testAuthenticate3() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token());
        when(tokenRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponseDto actualAuthenticateResult = authenticationUtil
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        verify(userRepository).findByEmail(Mockito.any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(authenticationManager).authenticate(Mockito.any());
    }

    @Test
    void testAuthenticate4() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token());
        tokenList.add(new Token());
        when(tokenRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponseDto actualAuthenticateResult = authenticationUtil
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        verify(userRepository).findByEmail(Mockito.any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(authenticationManager).authenticate(Mockito.any());
    }

    @Test
    void testAuthenticate5() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);
        Token token = mock(Token.class);
        doNothing().when(token).setExpired(anyBoolean());
        doNothing().when(token).setRevoked(anyBoolean());

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        when(tokenRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponseDto actualAuthenticateResult = authenticationUtil
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        verify(userRepository).findByEmail(Mockito.any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.any());
        verify(token).setExpired(anyBoolean());
        verify(token).setRevoked(anyBoolean());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(authenticationManager).authenticate(Mockito.any());
    }

    @Test
    void testRefreshToken() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationUtil.refreshToken(request, new Response());
        assertFalse(request.isAsyncStarted());
        assertTrue(request.isActive());
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getServletPath());
        assertEquals(80, request.getServerPort());
        assertEquals("localhost", request.getServerName());
        assertEquals("http", request.getScheme());
        assertEquals("", request.getRequestURI());
        assertEquals(80, request.getRemotePort());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("", request.getMethod());
        assertEquals(80, request.getLocalPort());
        assertEquals("localhost", request.getLocalName());
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertEquals("", request.getContextPath());
        assertEquals(-1L, request.getContentLengthLong());
    }

    @Test
    void testRefreshToken2() throws IOException {
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.any())).thenReturn("https://example.org/example");
        Response response = new Response();
        authenticationUtil.refreshToken(request, response);
        verify(request).getHeader(Mockito.any());
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response2.getOutputStream());
    }

    @Test
    void testRefreshToken3() throws IOException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);
        Token token = mock(Token.class);
        doNothing().when(token).setExpired(anyBoolean());
        doNothing().when(token).setRevoked(anyBoolean());

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        when(tokenRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.isTokenValid(Mockito.any(), Mockito.any())).thenReturn(true);
        when(jwtUtil.extractUsername(Mockito.any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.any())).thenReturn("Bearer ");
        authenticationUtil.refreshToken(request, new MockHttpServletResponse());
        verify(userRepository).findByEmail(Mockito.any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.any());
        verify(token).setExpired(anyBoolean());
        verify(token).setRevoked(anyBoolean());
        verify(jwtUtil).isTokenValid(Mockito.any(), Mockito.any());
        verify(jwtUtil).extractUsername(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
        verify(request).getHeader(Mockito.any());
    }

    @Test
    void testRefreshToken4() throws IOException {
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(mock(User.class)));
        when(jwtUtil.isTokenValid(Mockito.any(), Mockito.any())).thenReturn(false);
        when(jwtUtil.extractUsername(Mockito.any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.any())).thenReturn("Bearer ");
        authenticationUtil.refreshToken(request, mock(HttpServletResponse.class));
        verify(userRepository).findByEmail(Mockito.any());
        verify(jwtUtil).isTokenValid(Mockito.any(), Mockito.any());
        verify(jwtUtil).extractUsername(Mockito.any());
        verify(request).getHeader(Mockito.any());
    }

    @Test
    void testRefreshToken5() throws IOException {
        when(jwtUtil.extractUsername(Mockito.any())).thenReturn(null);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.any())).thenReturn("Bearer ");
        authenticationUtil.refreshToken(request, mock(HttpServletResponse.class));
        verify(jwtUtil).extractUsername(Mockito.any());
        verify(request).getHeader(Mockito.any());
    }
}

