package com.devLauran.SendCodeInGmail.controller;

import com.devLauran.SendCodeInGmail.service.EmailService;

public class EmailController {
    private final EmailService emailService;

    @PostMapping("login/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }
}
