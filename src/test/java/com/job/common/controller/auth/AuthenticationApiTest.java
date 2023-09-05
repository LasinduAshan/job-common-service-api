package com.job.common.controller.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.job.common.util.AuthenticationUtil;
import com.job.common.util.JwtUtil;
import jakarta.servlet.DispatcherType;

import java.io.IOException;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationApiTest {
    @Test
    void testRegister1() {

        UserRepository repository = mock(UserRepository.class);
        when(repository.save(Mockito.any())).thenReturn(new User());
        TokenRepository tokenRepository = mock(TokenRepository.class);
        when(tokenRepository.save(Mockito.any())).thenReturn(new Token());
        JwtUtil jwtUtil = mock(JwtUtil.class);
        when(jwtUtil.generateRefreshToken(Mockito.any())).thenReturn("ABC123");
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("ABC123");

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthenticationApi authenticationApi = new AuthenticationApi(new AuthenticationUtil(repository, tokenRepository,
                new BCryptPasswordEncoder(), jwtUtil, authenticationManager));
        ResponseEntity<AuthenticationResponseDto> actualRegisterResult = authenticationApi
                .register(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.ADMIN));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        AuthenticationResponseDto body = actualRegisterResult.getBody();
        assertEquals("ABC123", body.getRefreshToken());
        assertEquals("ABC123", body.getAccessToken());
        verify(repository).save(Mockito.any());
        verify(tokenRepository).save(Mockito.any());
        verify(jwtUtil).generateRefreshToken(Mockito.any());
        verify(jwtUtil).generateToken(Mockito.any());
    }

    @Test
    void testRegister2() {

        AuthenticationUtil service = mock(AuthenticationUtil.class);
        when(service.register(Mockito.any()))
                .thenReturn(new AuthenticationResponseDto("ABC123", "ABC123"));
        AuthenticationApi authenticationApi = new AuthenticationApi(service);
        ResponseEntity<AuthenticationResponseDto> actualRegisterResult = authenticationApi
                .register(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.CONSULTANT));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        verify(service).register(Mockito.any());
    }

    @Test
    void testAuthenticate() {

        AuthenticationUtil service = mock(AuthenticationUtil.class);
        when(service.authenticate(Mockito.any()))
                .thenReturn(new AuthenticationResponseDto("ABC123", "ABC123"));
        AuthenticationApi authenticationApi = new AuthenticationApi(service);
        ResponseEntity<AuthenticationResponseDto> actualAuthenticateResult = authenticationApi
                .authenticate(new AuthenticationRequestDto("jane.doe@example.com", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(200, actualAuthenticateResult.getStatusCodeValue());
        verify(service).authenticate(Mockito.any());
    }

    @Test
    void testRefreshToken1() throws IOException {

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationApi authenticationApi = new AuthenticationApi(
                new AuthenticationUtil(repository, tokenRepository, passwordEncoder, new JwtUtil(), authenticationManager));
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationApi.refreshToken(request, new Response());
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

        AuthenticationUtil service = mock(AuthenticationUtil.class);
        doNothing().when(service).refreshToken(Mockito.any(), Mockito.any());
        AuthenticationApi authenticationApi = new AuthenticationApi(service);
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationApi.refreshToken(request, new Response());
        verify(service).refreshToken(Mockito.any(), Mockito.any());
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
}

