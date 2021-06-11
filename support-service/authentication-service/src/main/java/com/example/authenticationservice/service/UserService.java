package com.example.authenticationservice.service;

import dto.SignUpRequestDTO;
import dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
//    Mono<UserDTO> register(SignUpRequestDTO signUpRequestDTO);

//    Mono<ServerResponse> login(ServerRequest serverRequest);

    //    public Mono<UserDTO> signup(SignUpRequestDTO signUpRequestDTO);
    Mono<ServerResponse> signUp(ServerRequest request);

    Mono<ServerResponse> login(ServerRequest serverRequest);

    Mono<ServerResponse> getAllUser(ServerRequest serverRequest);
//    public Mono<ServerResponse> getUser(ServerRequest serverRequest);
}
