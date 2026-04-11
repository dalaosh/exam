package com.mc.esuser.feignClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author machao
 * * @datetime 2025/1/9 19:57
 * * inform
 */

@FeignClient(name = "es-mail", url = "${services.es-mail.url:http://localhost:9081}")
public interface EmailFeignClient {
    @PostMapping("/email/sendSimpleMail")
    String sendSimpleMail(@RequestParam("address") String address,
                          @RequestParam("subject") String subject,
                          @RequestParam("body") String body);

    @PostMapping("/email/sendAttachmentMail")
    String sendAttachmentMail(@RequestParam("address") String address,
                              @RequestParam("subject") String subject,
                              @RequestParam("body") String body,
                              @RequestPart("attach") MultipartFile attach);
}
