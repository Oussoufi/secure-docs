package mr.mayatech.securedoc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.mayatech.securedoc.exception.ApiException;
import mr.mayatech.securedoc.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static mr.mayatech.securedoc.constant.EmailConstant.NEW_USER_ACCOUNT_VERIFICATION;
import static mr.mayatech.securedoc.constant.EmailConstant.PASSWORD_RESET_REQUEST;
import static mr.mayatech.securedoc.utils.EmailUtils.getEmailMessage;
import static mr.mayatech.securedoc.utils.EmailUtils.getPasswordMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender sender;
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            sender.send(message);
        } catch (Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("Unable to send email");
        }

    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String email, String token) {

        try {
            var message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getPasswordMessage(name, host, token));
            sender.send(message);
        } catch (Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("Unable to send email");
        }

    }
}
