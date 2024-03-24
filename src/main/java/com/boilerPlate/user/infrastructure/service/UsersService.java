package com.boilerPlate.user.infrastructure.service;

import com.boilerPlate.global.error.errorCode.ErrorCode;
import com.boilerPlate.global.error.exception.NotExistUserException;
import com.boilerPlate.global.utils.Aes256;
import com.boilerPlate.user.infrastructure.repository.UsersRepository;
import com.boilerPlate.user.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final Aes256 aes256;

    @Transactional
    public void save(UserRequestDto userRequestDto) {
        if (usersRepository.existsByUserId(userRequestDto.getUserId())) {
            throw new NotExistUserException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }

        usersRepository.save(aes256.userEncryptAes256(userRequestDto));
    }
}
