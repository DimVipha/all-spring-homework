package co.istad.demomobilebanking.feature.user;

import co.istad.demomobilebanking.base.BaseMessage;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import co.istad.demomobilebanking.feature.user.DTO.UserDetailResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {
    void createNew(UserCreateRequest userCreateRequest);
//    UserResponse updateByUuid(String uuid,UserUpdateRequest userUpdateRequest);


    Page<UserResponse> findList(int page, int limit);
    UserDetailResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserDetailResponse findByUuid(String uuid);

    BaseMessage blockByUuid(String uuid);

    void deleteByUuid(String uuid);
    BaseMessage disableUserByUuid(String uuid);
    BaseMessage enableUserByUuid(String uuid);

}
