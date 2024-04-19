package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.Account;
import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.domain.UserAccount;
import co.istad.demomobilebanking.feature.account.dto.AccountResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import co.istad.demomobilebanking.feature.user.DTO.UserDetailResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // SourceType = UserCreateRequest (Parameter)
    // TargetType = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    void fromUserCreateRequest2(@MappingTarget User user, UserCreateRequest userCreateRequest);

    UserDetailResponse toUserDetailResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList) {
        // YOUR LOGIC OF MAPPING HERE...
        return toUserResponse(userAccountList.get(0).getUser());
    }

    //@Mapping(source = "userAccountList",target = "user",qualifiedByName ="mapUserResponse" )

}
