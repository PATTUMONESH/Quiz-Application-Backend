package com.example.quiz_app_backend.Service.Impl;


import com.example.quiz_app_backend.Entity.*;
import com.example.quiz_app_backend.Repository.QuestionListRepository;
import com.example.quiz_app_backend.Repository.SubjectRepository;
import com.example.quiz_app_backend.Repository.UserRepository;
import com.example.quiz_app_backend.Repository.UserScoreRepository;
import com.example.quiz_app_backend.Service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

@Autowired
    private UserScoreRepository userScoreRepository;

@Autowired
private QuestionListRepository questionListRepository;

@Autowired
private SubjectRepository subjectRepository;

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private Configuration config;
//
//    @Value("$(pattumonesh@gmail.com)")
//    private String fromMail;
//    public void sendMailUser(String mail, MailStructure mailStructure){
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setFrom(fromMail);
//        simpleMailMessage.setSubject(mailStructure.getSubject());
//        simpleMailMessage.setText(mailStructure.getMessage());
//        simpleMailMessage.setTo(mail);
//        javaMailSender.send(simpleMailMessage);
//
//    }



    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMailUser(String mail, MailStructure mailStructure) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("name",mailStructure.getName());
            model.put("score", mailStructure.getScore());
            model.put("description",mailStructure.getDescription());

            model.put("correctAnswer",mailStructure.getCorrectAnswer());
            model.put("wrongAnswer",mailStructure.getWrongAnswer());
            model.put("unAtteptedQuestion",mailStructure.getUnAtteptedQuestion());
            model.put("totalQuestions",mailStructure.getTotalQuestions());

            Template template = freemarkerConfig.getTemplate("scoreEmailTemplate.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(mail);
            helper.setSubject(mailStructure.getSubject());
            helper.setText(html, true);

            javaMailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
















    public  UserImpl(UserRepository userRepository,UserScoreRepository userScoreRepository){
        this.userScoreRepository=userScoreRepository;
        this.userRepository=userRepository;
    }



    @Override
    public UserDetails saveUser(UserDetails userDetails) {

        return userRepository.save(userDetails);
    }

    @Override
    public UserDetails fetchUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails fetchUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

//    @Override
//    public UserDetails fetchUserEmailById() {
//        return userRepository.findByEmailId();
//    }


    public String getUserEmailById(Long userId) {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        userDetails.getFirstName();

        return userDetails.getEmail();
    }





    @Override
    public UserScore saveUserScore(UserScore userScore, Long userId) {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userScore.setFirstName(userDetails.getFirstName());
        userScore.setLastName(userDetails.getLastName());
        userScore.setUserDetails(userDetails);
        return userScoreRepository.save(userScore);


    }


    public List<UserScore> getAllUserScores() {
        return userScoreRepository.findAll();
    }

    public QuestionsConfig saveQuestions(QuestionsConfig questionsConfig){
        return questionListRepository.save(questionsConfig);
    }

    public Optional<QuestionsConfig> getQuestionByQid(Integer Id){
        return questionListRepository.findById(Id);
    }


    public List<QuestionsConfig> getAllQuestions() {
        return questionListRepository.findAll();
    }
    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }






}
