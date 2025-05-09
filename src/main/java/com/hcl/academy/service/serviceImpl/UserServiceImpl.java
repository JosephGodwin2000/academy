package com.hcl.academy.service.serviceImpl;

import com.hcl.academy.dto.request.RegisterDto;
import com.hcl.academy.entity.Users;
import com.hcl.academy.repository.UsersRepository;
import com.hcl.academy.service.UserService;
import com.hcl.academy.utits.AppUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDto request) {
        boolean emailExist = usersRepository.existsByEmail(request.getEmailId());
        boolean phoneNoExist = usersRepository.existsByContactNumber(request.getContactNumber());

        if(emailExist) {
            log.info("Email {} is already registered", request.getEmailId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already register in another account");
        }

        if(phoneNoExist) {
            log.info("Phone Number {} is already registered", request.getContactNumber());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Contact Number is already register in another account");
        }

        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setEmail(request.getEmailId());
        user.setContactNumber(request.getContactNumber());
        user.setPassword(passwordEncoder.encode("Joseph"));
        user.setAssociateId(usersRepository.findLatestAssociateId().map(e->AppUtils.generateUniqueId(e.getAssociateId())).orElse("A00001"));

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(sender);
            helper.setTo(request.getEmailId());
            helper.setSubject("Account Created Successfully");

            Context context = new Context();
            context.setVariable("name", request.getFirstName() + " " + request.getLastName());
            context.setVariable("associateId", user.getAssociateId());
            context.setVariable("password", user.getPassword());

            String htmlContent = templateEngine.process("account-creation-email", context);
            helper.setText(htmlContent, true);

            // Sending the mail
            mailSender.send(mimeMessage);
            usersRepository.save(user);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

    }
}
