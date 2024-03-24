package com.boilerPlate.login;

import com.boilerPlate.global.error.errorCode.ErrorCode;
import com.boilerPlate.global.error.exception.NotExistUserException;
import com.boilerPlate.user.infrastructure.domain.Users;
import com.boilerPlate.user.infrastructure.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userId)  {
        Users users = usersRepository.findByUserId(userId).orElseThrow(() -> new NotExistUserException(ErrorCode.MEMBER_NOT_EXIST));
        return Objects.nonNull(users) ? new CustomUserDetails(users) : null;
    }
}
