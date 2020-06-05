package com.lineate.api.endpoints;

import com.lineate.api.core.domain.examples.onetoone.mapsid.User;
import com.lineate.api.core.domain.examples.onetoone.mapsid.UserType;
import com.lineate.api.dto.response.UserLoginResponse;
import com.lineate.api.dto.response.UserResponse;
import com.lineate.api.ex.BadRequestException;
import com.lineate.api.ex.ErrorCode;
import com.lineate.api.services.TokenService;
import com.lineate.api.services.UserService;
import com.lineate.api.dto.ExtendedUserDetails;
import com.lineate.api.dto.request.UserLoginRequest;
import com.lineate.api.dto.request.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static com.lineate.api.SecurityConstants.HEADER_STRING;
import static com.lineate.api.SecurityConstants.SET_AUTH_HEADER_STRING;
import static com.lineate.api.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEndpoint.class);
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserEndpoint(
        UserService userService,
        TokenService tokenService,
        BCryptPasswordEncoder cryptPasswordEncoder
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @PostMapping(
        value = "/registration",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
        )
    public void registerUser(
        @Valid @RequestBody UserRegistrationRequest request,
        HttpServletResponse response
    ) throws BadRequestException {
        LOGGER.info("Start method POST /user/registration");
        if (userService.isEmailExist(request.getEmail())) {
            throw new BadRequestException(
                ErrorCode.EMAIL_ALREADY_IN_USE,
                "email",
                "Email " + request.getEmail() + " already in use"
            );
        }

        User user = new User(
            request.getEmail(),
            cryptPasswordEncoder.encode(request.getPassword()),
            request.getName(),
            UserType.USER
        );
        user = userService.addUser(user);

        String token = tokenService.getToken(user);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    @PostMapping(
        value = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
        )
    @CrossOrigin(origins = "*", maxAge = 3600)
    public UserLoginResponse loginUser(
        @RequestBody UserLoginRequest request,
        HttpServletResponse response
    ) throws BadRequestException {
        User user = userService.getUserByEmail(request.getEmail());
        if (user == null) {
            throw new BadRequestException(ErrorCode.INCORRECT_LOGIN_OR_PASSWORD, "login or password");
        }
        if (!cryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(ErrorCode.INCORRECT_LOGIN_OR_PASSWORD, "login or password");
        }

        String token = tokenService.getToken(user);
        response.addHeader(SET_AUTH_HEADER_STRING, token);
        UserLoginResponse loginDtoResponse = new UserLoginResponse(token);
        LOGGER.info("User " +  request.getEmail() + " has been logged in");
        return loginDtoResponse;
    }

    @GetMapping(
        value = "/me",
        produces = MediaType.APPLICATION_JSON_VALUE
        )
    public UserResponse getUser(
        @AuthenticationPrincipal ExtendedUserDetails userDetails
    ) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return new UserResponse(null, user.getEmail(), user.getName());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
        )
    public List<UserResponse> getUsers(
        @AuthenticationPrincipal ExtendedUserDetails userDetails
    ) {
        return userService.getAllUsers();
    }
}
