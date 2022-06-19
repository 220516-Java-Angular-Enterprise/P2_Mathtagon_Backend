package com.revature.mathtagon.user;

import com.revature.mathtagon.auth.dtos.requests.LoginRequest;
import com.revature.mathtagon.user.dtos.requests.NewUserRequest;
import com.revature.mathtagon.util.customexceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public Users login(LoginRequest request){
        Users user = null;
        if(!isNotDuplicateUsername(request.getUsername()) || !isPassValid(request.getPassword())) throw new AuthenticationException("Username is taken or password is invalid");
        user = userRepository.getUserAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("Invalid credentials");
        return user;
    }

    public Users registerUser(NewUserRequest request){
        Users user = request.takeUser();

        if(isNotDuplicateUsername(user.getUsername())){
            if (isUserValid(user.getUsername())){
                if(isPassValid(user.getPassword())){
                    user.setUserID(UUID.randomUUID().toString());
                    userRepository.saveUser(user.getUserID(),user.getUsername(),user.getPassword(),user.getEmail(),user.getFullName(),user.getAge());
                } else throw new AuthenticationException(" Invalid password. password must have at least 8 characters");
            }else throw new AuthenticationException("Invalid username. username must have atleast 8 - 20 characters");

        }else throw new AuthenticationException("Username is already in use");
        return  user;
    }

    public List<Users> getAllUsers(){
        return(List<Users>) userRepository.findAll();
    }

    private boolean isUserValid(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isPassValid(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    private boolean isNotDuplicateUsername(String username){
        return !userRepository.getAllUsers().contains(username);
    }

}
