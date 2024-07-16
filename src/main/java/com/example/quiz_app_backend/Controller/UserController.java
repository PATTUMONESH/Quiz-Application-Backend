package com.example.quiz_app_backend.Controller;

import com.example.quiz_app_backend.Entity.Response;
import com.example.quiz_app_backend.Entity.UserDetails;
import com.example.quiz_app_backend.Entity.UserScore;
import com.example.quiz_app_backend.Exception.BadLoginCredentials;
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
    public UserController(UserImpl userImpl){

        this.userImpl=userImpl;
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

    public UserDetails loginUser(@RequestBody UserDetails userDetails){

        String tempEmail=userDetails.getEmail();
        String tempPassword=userDetails.getPassword();
        UserDetails userObject=null;
        if(tempEmail!=null && tempPassword!=null){
            userObject= userImpl.fetchUserByEmailAndPassword(tempEmail,tempPassword);
        }
        if (userObject == null) {
            throw new BadLoginCredentials("bad credentials");
        }
        return userObject;
    }


    @PostMapping("/userscore/{userId}")
    public ResponseEntity<Response> saveUserScoreById(@RequestBody UserScore userScore, @PathVariable Long userId) {
        userImpl.saveUserScore(userScore, userId);
        Response addScoreresponse = new Response();
        addScoreresponse.setMessage("user Score added successful");
        addScoreresponse.setStatus(HttpStatus.CREATED.value());
//        System.out.println(response.getStatus());
        return new ResponseEntity<>(addScoreresponse, HttpStatus.OK);
    }

    @GetMapping("/usescorelist")
    public List<UserScore> getAllUserScores() {
        return userImpl.getAllUserScores();
    }

//    @GetMapping("/userscore/{userId}")
//    public ResponseEntity<Response> getUserById(@PathVariable long userId){
//
//
//
//
//    }







}
