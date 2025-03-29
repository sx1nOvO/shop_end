package com.mty.common;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @USER: SHX
 * @CREATE_DATE: 2025/3/31 11:51
 */
@Configuration
public class SmsInfoClient {

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;

    @Bean
    public Client getClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);

        config.endpoint = "dysmsapi.aliyuncs.com";

        return new Client(config);
    }
}
