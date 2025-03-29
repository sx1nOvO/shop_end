package com.mty.service;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @USER: SHX
 * @CREATE_DATE: 2025/3/31 16:55
 */
@Service
public class SmsService {

    @Autowired
    private Client client;

    @Autowired
    private RedisTemplate redisTemplate;

    public void sendCode(String phone, String code) {

        //将验证吗code存到redis中
        redisTemplate.opsForValue().set("verify:phone:"+phone,code,5, TimeUnit.MINUTES);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setTemplateParam("{\"code\":\"" + code + "\"}");

        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            // 这里可以添加更多的错误检查，例如检查 sendSmsResponse 的状态码等
            if (sendSmsResponse.getBody().getCode().equals("OK")) {
                System.out.println(sendSmsResponse.getBody().getMessage());
                System.out.println("短信发送成功");
            } else {
                System.out.println("短信发送失败，原因：" + sendSmsResponse.getBody().getMessage());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
