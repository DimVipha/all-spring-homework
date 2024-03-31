package co.istad.demomobilebanking.feature.user;

import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;


public interface UserService {
    void createNew(UserCreateRequest userCreateRequest);
}
