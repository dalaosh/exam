package com.mc.esmail.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Tag(name = "发送邮件")
@RequiredArgsConstructor
@RestController
@RefreshScope
@RequestMapping("/email")
public class EmailController {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String account;


    @Parameters({
            @Parameter(name = "address", description = "收件地址", required = true),
            @Parameter(name = "subject", description = "标题", required = true),
            @Parameter(name = "body", description = "正文", required = true)
    })
    @Operation(summary = "发送简单邮件", description = "将邮件进行发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "发送成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("sendSimpleMail")
    public String sendSimpleMail(@RequestParam("address") String address,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("body") String body) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(account);
        smm.setTo(address);
        smm.setSubject(subject);
        smm.setText(body);
        javaMailSender.send(smm);
        return "发送成功";
    }

    @Parameters({
            @Parameter(name = "address", description = "收件地址", required = true),
            @Parameter(name = "subject", description = "标题", required = true),
            @Parameter(name = "body", description = "正文", required = true),
            @Parameter(name = "attach", description = "附件", required = true)
    })
    @Operation(summary = "发送带附件的邮件", description = "将邮件进行发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "发送成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("sendAttachmentMail")
    public String sendAttachmentMail(@RequestParam("address") String address,
                                     @RequestParam("subject") String subject,
                                     @RequestParam("body") String body,
                                     @RequestPart("attach") MultipartFile attach) throws MessagingException, IOException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(account);
        mimeMessageHelper.setTo(new String[]{address});
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        //文件路径
        byte[] bytes = attach.getBytes();
        /*文件名设置*/
        String name = attach.getOriginalFilename();
        mimeMessageHelper.addAttachment(name, new ByteArrayResource(bytes));
        log.info("fileName:{}", name);
        javaMailSender.send(mimeMailMessage);
        return "发送成功";
    }
}
