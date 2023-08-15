package com.zerobase.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailComponents {

    private final JavaMailSender javaMailSender;
    public void sendMailTest() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("guswlsdl4808@gmail.com");
        msg.setSubject("안녕하세요. 제로베이스 입니다.");
        msg.setText("안녕하세요. 제로베이스 입니다. 반갑습니다.");

        javaMailSender.send(msg);
    }

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;
        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        try {
            javaMailSender.send(msg); // 메시지 보내고
            result = true; // 결과 true 로 변환
        } catch (Exception e) { // 예외 발생하면
            System.out.println(e.getMessage()); // 예외 메시지 출력하고
        }
        return result; // 결과는 false 로 반환됨.
    }

}
