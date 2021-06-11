package com.example.authenticationservice.service.impl;


import com.devskiller.friendly_id.FriendlyId;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.entity.UserRole;
import com.example.authenticationservice.exception.UserAlreadyExistException;
import com.example.authenticationservice.exception.UserNotFoundException;
import com.example.authenticationservice.jwt.JwtTokenProvider;
import com.example.authenticationservice.mapper.UserMapper;
import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.service.UserService;
import dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//import com.example.demo.jwt.JwtAuthenticationFilter;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider tokenProvider;

    private String encodePassword(SignUpRequestDTO signUpRequest) {
        return passwordEncoder.encode(signUpRequest.getPassword());
    }

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginRequestDTO> loginRequest = request.bodyToMono(LoginRequestDTO.class);
        return loginRequest.flatMap(login -> userRepository.findByUsername(login.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromObject(new LoginResponseDTO(tokenProvider.generateAccessToken(user),
                                        tokenProvider.generateRefreshToken(user))));
                    } else {
                        return ServerResponse.badRequest()
                                .body(BodyInserters.fromObject(new DataResponseDTO("", "Invalid credentials",
                                        400, null)));
                    }
                }).switchIfEmpty(ServerResponse.badRequest()
                        .body(BodyInserters.fromObject(new DataResponseDTO("", "User does not exist",
                                400, "")))));
    }

    @Override
    public Mono<ServerResponse> getAllUser(ServerRequest serverRequest) {
        Flux<UserDTO> userDTOs = UserMapper.convertToDTOs(userRepository.findAll());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userDTOs, UserDTO.class);
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(SignUpRequestDTO.class).map(user -> User.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .userRole(UserRole.ROLE_USER)
                .password(encodePassword(user))
                .phoneNumber(user.getPhoneNumber())
                .uid(FriendlyId.createFriendlyId())
                .build());
        return userMono.flatMap(user -> userRepository.findByUsername(user.getUsername())
                .flatMap(dbUser -> ServerResponse.badRequest().body(BodyInserters.fromObject(new DataResponseDTO(
                        "", "User already exist", 400, null))))
                .switchIfEmpty(userRepository.save(user)
                        .flatMap(savedUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromObject(UserMapper.convertToDTO(savedUser))))));
    }

//    @Override
//    public Mono<ServerResponse> getUser(ServerRequest request){
//        String accessToken = request.headers().firstHeader("Authorization");
////        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        String jwt=null;
//        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
//            jwt =accessToken.substring(7);
//        }
//        String username = tokenProvider.getUsernameFromSubjectJWT(jwt);
//        return userRepository.findByUsername(username).flatMap(user ->
//                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromObject(UserMapper.convertToDTO(user))));
//    }

}
