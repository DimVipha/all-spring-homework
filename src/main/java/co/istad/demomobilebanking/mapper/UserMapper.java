package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest request);

//    void fromUserCreatRequest2(@MappingTarget User user);

//    UserResponse toUserDetailResponse(User user);
}
