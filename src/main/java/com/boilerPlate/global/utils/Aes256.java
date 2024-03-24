package com.boilerPlate.global.utils;

import com.boilerPlate.user.infrastructure.domain.Users;
import com.boilerPlate.user.request.UserRequestDto;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class Aes256 implements PasswordEncoder {
    @Value("${aes256.key}")
    private String privateKey_256;

    public String aesCBCDecode(String encodeText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(privateKey_256.getBytes("UTF-8"), "AES");
        IvParameterSpec IV = new IvParameterSpec(privateKey_256.substring(0, 16).getBytes());

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey, IV);

        byte[] decodeByte = Hex.decodeHex(encodeText.toCharArray());
        return new String(c.doFinal(decodeByte), "UTF-8");
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(privateKey_256.getBytes("UTF-8"), "AES");
            IvParameterSpec IV = new IvParameterSpec(privateKey_256.substring(0, 16).getBytes());

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secretKey, IV);

            byte[] encrpytionByte = c.doFinal(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(encrpytionByte);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword) ? true : false;
    }
    public Users userEncryptAes256(UserRequestDto userRequestDto) {
        try {
            String userserId = userRequestDto.getUserId();
            String encryptUserPassword = encode(userRequestDto.getPassword());
            String encryptUserName = encode(userRequestDto.getName());
            String encryptUserRegNo = encode(userRequestDto.getRegNo());

            return Users.builder()
                    .userId(userserId)
                    .password(encryptUserPassword)
                    .name(encryptUserName)
                    .regNo(encryptUserRegNo)
                    .build();

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
