package mr.mayatech.securedoc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.mayatech.securedoc.domain.Response;
import mr.mayatech.securedoc.dtorequest.UserRequest;
import mr.mayatech.securedoc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


import static java.util.Collections.emptyMap;
import static mr.mayatech.securedoc.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/users"})
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Response> saveUser(@RequestBody @Valid UserRequest user, HttpServletRequest request){
        userService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return ResponseEntity
                .created(
                        getUri()
                )
                .body(
                        getResponse(
                                request,
                                emptyMap(),
                                "Account created. Check your email to enable your account",
                                CREATED)
                );

    }

    private URI getUri(){
        return URI.create("");
    }
}