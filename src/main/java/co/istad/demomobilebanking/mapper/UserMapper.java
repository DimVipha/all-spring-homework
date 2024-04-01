package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import co.istad.demomobilebanking.feature.user.DTO.UserDetailResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest request);

//    void fromUserCreatRequest2(@MappingTarget User user);

//    UserResponse toUserDetailResponse(User user);


//    UserDetailsResponse toUserDetailsResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

    UserDetailResponse toUserDetailResponse(User user);

//    List<UserResponse> toUserResponse();
}
