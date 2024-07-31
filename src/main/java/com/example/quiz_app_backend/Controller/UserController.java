package com.example.quiz_app_backend.Controller;

import com.example.quiz_app_backend.Dto.QuestionRequest;
import com.example.quiz_app_backend.Entity.*;
import com.example.quiz_app_backend.Exception.BadLoginCredentials;
import com.example.quiz_app_backend.Exception.ResourceNotFoundException;
import com.example.quiz_app_backend.Exception.UserAlreadyExistsException;
import com.example.quiz_app_backend.Service.Impl.UserImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping()
public class UserController {
    private UserImpl userImpl;
    public UserController(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    @PostMapping("/RegisterUsers")
    public ResponseEntity<Response> registerUser(@RequestBody UserDetails userDetails) {
        String tempEmailId = userDetails.getEmail();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            UserDetails userObj = userImpl.fetchUserByEmail(tempEmailId);
            if (userObj != null) {
                throw new UserAlreadyExistsException("User with " + tempEmailId + " already exists");
            }
        }
        userImpl.saveUser(userDetails);
        Response response = new Response();
        response.setMessage("user created successful");
        response.setStatus(HttpStatus.CREATED.value());
//        System.out.println(response.getStatus());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/Login")

    public UserDetails loginUser(@RequestBody UserDetails userDetails) {

        String tempEmail = userDetails.getEmail();
        String tempPassword = userDetails.getPassword();
        UserDetails userObject = null;
        if (tempEmail != null && tempPassword != null) {
            userObject = userImpl.fetchUserByEmailAndPassword(tempEmail, tempPassword);
        }
        if (userObject == null) {
            throw new BadLoginCredentials("bad credentials");
        }
        return userObject;
    }
//    @PostMapping("/userscore/{userId}")
//    public ResponseEntity<Response> saveUserScoreById(@RequestBody UserScore userScore, @PathVariable Long userId) {
//        userImpl.saveUserScore(userScore, userId);
//        String email=userImpl.getUserEmailById(userId);
//
//        // Send email
//        MailStructure mailStructure = new MailStructure();
//        mailStructure.setSubject("Score Submission Confirmation");
//        mailStructure.setMessage("Your score is "+userScore.getScore());
//        userImpl.sendMailUser(email, mailStructure);
//
//
//        Response mailResponse = new Response();
//        mailResponse.setMessage("Score submitted and email sent successfully");
//        mailResponse.setStatus(HttpStatus.CREATED.value());
//        return new ResponseEntity<>(mailResponse, HttpStatus.OK);
//        Response addScoreresponse = new Response();
//        addScoreresponse.setMessage("user Score added successful");
//        addScoreresponse.setStatus(HttpStatus.CREATED.value());
//       System.out.println(addScoreresponse.getStatus());
//        return new ResponseEntity<>(addScoreresponse, HttpStatus.OK);
// }


//    @PostMapping("/userscore/{userId}")
//    public ResponseEntity<Response> saveUserScoreById(@RequestBody UserScore userScore, @PathVariable Long userId) {
//        userImpl.saveUserScore(userScore, userId);
//
//
//        String email = userImpl.getUserEmailById(userId);


        // Send email
        MailStructure mailStructure = new MailStructure();


        mailStructure.setSubject("Score Submission Confirmation");
        mailStructure.setMessage("Your score is " + userScore.getScore());


        mailStructure.setName(userScore.getFirstName().toUpperCase().concat(" ").concat(userScore.getLastName().toUpperCase()));


        System.out.println(userScore.getFirstName());

        mailStructure.setScore(userScore.getScore());

        int score = userScore.getScore();
        String description = score > 60 ? "Congratulations,you are shotlisted for next round" : "Better luck next time,you are not qualified in screening test";
        System.out.println("Description: " + description);
        mailStructure.setDescription(description);

        System.out.println(userScore.getScore());
        mailStructure.setCorrectAnswer(userScore.getCorrect());
        System.out.println(userScore.getCorrect());

        mailStructure.setWrongAnswer(userScore.getInCorrect());

        System.out.println(userScore.getInCorrect());

        mailStructure.setUnAtteptedQuestion(userScore.getNotVisited());
        System.out.println(userScore.getNotVisited());
        mailStructure.setTotalQuestions(userScore.getTotal());
        System.out.println(userScore.getTotal());

        userImpl.sendMailUser(email, mailStructure);

        Response mailResponse = new Response();
        mailResponse.setMessage("Score submitted and email sent successfully");
        mailResponse.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(mailResponse, HttpStatus.OK);
    }


    @GetMapping("/usescorelist")
    public List<UserScore> getAllUserScores() {
        return userImpl.getAllUserScores();
    }


//    @PostMapping("/send/{mail}")
//    public ResponseEntity<Response> sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
//        userImpl.sendMailUser(mail,mailStructure);
//        Response mailResponse = new Response();
//        mailResponse.setMessage("mail sent successfully");
//        mailResponse.setStatus(HttpStatus.CREATED.value());
//        return new ResponseEntity<>(mailResponse, HttpStatus.OK);
//    }


//    @PostMapping("/addQuestions")
//    public ResponseEntity<Response> storeQuestions(@RequestBody QuestionsConfig questionsConfig) {
//        userImpl.saveQuestions(questionsConfig);
//        Response response = new Response();
//        response.setMessage("Questions added successfully");
//        response.setStatus(HttpStatus.CREATED.value());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }

    @PostMapping("/addQuestions")
    public ResponseEntity<Response> storeQuestions(@RequestBody QuestionRequest questionRequest) {

        userImpl.saveQuestions(questionRequest);
        Response response = new Response();
        response.setMessage("Questions added successfully");
        response.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PostMapping("/addSubject")
//    public ResponseEntity<Response> addSubject(@RequestBody Subject subject){
//
//        userImpl.saveSubject(subject);
//        Response response = new Response();
//        response.setMessage("Question set added successfully");
//        response.setStatus(HttpStatus.CREATED.value());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }

    




    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<QuestionsConfig> getQuestionById(@RequestParam Integer questionId) {
        QuestionsConfig questionsConfig = userImpl.getQuestionByQid(questionId).orElseThrow(() -> new ResourceNotFoundException("Question does not exists with this id"));

        return ResponseEntity.ok(questionsConfig);
    }


    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionsConfig>> getAllQuestions() {
        List<QuestionsConfig> questions = userImpl.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}

















//    @GetMapping("/userscore/{userId}")
//    public ResponseEntity<Response> getUserById(@PathVariable long userId){
//    }










