package co.istad.demomobilebanking.feature.user;

import co.istad.demomobilebanking.base.BaseMessage;
import co.istad.demomobilebanking.domain.Transaction;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import co.istad.demomobilebanking.feature.user.DTO.UserDetailResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserUpdateRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    Page<UserResponse> findList(@RequestParam(required = false, defaultValue = "0")int page,
                                @RequestParam(required = false, defaultValue = "1") int limit){
       return userService.findList(page, limit);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNew(userCreateRequest);
    }
    @PatchMapping("/{uuid}")
    UserDetailResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }


    @GetMapping("/{uuid}")
    UserDetailResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BaseMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    
    // disable user By uuid( soft delete)("{uuid}/disable")
    @PutMapping("/{uuid}/disable")
    BaseMessage disableUserByUuid(@PathVariable String uuid){
        return userService.disableUserByUuid(uuid);
    }


    // enable user by uuid ("{uuid}/enable") is deleted=true
    @PutMapping("/{uuid}/enable")
    BaseMessage enableUserByUuid(@PathVariable String uuid){
        return  userService.enableUserByUuid(uuid);
    }

    // delete by uuid(hard delete)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void DeleteByUuid( @PathVariable String uuid){
        userService.deleteByUuid(uuid);
    }







}
