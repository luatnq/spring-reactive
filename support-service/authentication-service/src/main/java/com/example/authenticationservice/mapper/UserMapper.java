package com.example.authenticationservice.mapper;


import com.example.authenticationservice.entity.User;
import dto.UserDTO;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;

public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static Flux<UserDTO> convertToDTOs(Flux<User> users) {
        return users.map(user -> UserDTO.builder()
        .uid(user.getUid())
        .userRole(user.getUserRole().name())
        .username(user.getUsername())
        .phoneNumber(user.getPhoneNumber())
        .fullName(user.getFullName())
        .email(user.getEmail())
        .build());
    }
}
