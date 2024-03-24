package com.boilerPlate.global.config;

import com.boilerPlate.global.error.FeignClientExceptionErrorDecoder;
import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Configuration
@EnableFeignClients(basePackages = "com.boilerPlate")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class FeignConfiguration {
    @Value("${scrap.x-api-key}")
    private String apiKey;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientExceptionErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("X-API-KEY", apiKey);
            template.header("Content-Type", "application/json");
        };
    }

    @Bean
    public feign.Client client() throws KeyManagementException, NoSuchAlgorithmException {
        return new Client.Default(sslContextFactory(), (hostname, session) -> true);
    }
    private SSLSocketFactory sslContextFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ssl_ctx = SSLContext.getInstance("TLS");
        TrustManager[] certs = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String t) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String t) {
                    }
                }};
        ssl_ctx.init(null, certs, new SecureRandom());
        return ssl_ctx.getSocketFactory();
    }
}