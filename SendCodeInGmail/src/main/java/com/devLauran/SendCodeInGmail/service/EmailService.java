package com.devLauran.SendCodeInGmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import java.util.Random;
@Service
public class EmailService{
    @Autowired
    JavaMailSender emailSender;

    public static  final String ePw = createKey();

    private MimeMessage createMessage(String to)throws Exception
    {
        System.out.println("보내는 대상: " + to);
        System.out.println("인증 번호: " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);
        message.setSubject("이메일 인증 테스트입니다!!");

        String msgg = "";
        msgg = msgg + "<div style='margin:20px;'>";
        msgg = msgg + "<h1> 안녕하세요!! INU X LikeLion 입니다!! </h1>";
        msgg = msgg + "<br>";
        msgg = msgg + "<p>인증번호는 아래와 같습니다. 인증번호를 복사해 입력해주세요<p>";
        msgg = msgg + "<br>";
        msgg = msgg + "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg = msgg + "<h3 style='color:blue;'>인증 코드입니다.</h3>";
        msgg = msgg + "<div style='font-size:130%'>";
        msgg = msgg + "CODE : <strong>";
        msgg = msgg + ePw+"</strong><div><br/> ";
        message.setText(msgg, "UTF-8", "html");
        message.setFrom(new InternetAddress("properties에 입력한 이메일", "kimseonhee126"));

        return message;
    }

    public static String createKey()
    {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++)
        {
            int index = random.nextInt(3); // 0~2까지 랜덤

            switch (index)
            {
                case 0:
                    // a  ~  z
                    key.append((char) (int) (random.nextInt(26)) + 97);
                    break;
                case 1:
                    // A ~ Z
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((random.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }

    public String sendSimpleMessage(String to)throws Exception
    {
        MimeMessage message = createMessage(to);
        try
        {
            emailSender.send(message);
        }
        catch (MailException es)
        {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
