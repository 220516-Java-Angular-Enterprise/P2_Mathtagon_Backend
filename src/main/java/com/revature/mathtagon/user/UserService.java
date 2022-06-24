package com.revature.mathtagon.user;

import com.revature.mathtagon.auth.dtos.requests.LoginRequest;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.user.dtos.requests.NewUserRequest;
import com.revature.mathtagon.util.annotations.Inject;
import com.revature.mathtagon.util.customexceptions.AuthenticationException;
import com.revature.mathtagon.util.customexceptions.InvalidRequestException;
import com.revature.mathtagon.util.customexceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Transactional
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Inject
    private final UserRepository userRepository;

    @Inject
    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}


    public User login(LoginRequest request){
        User user = null;
        if(!isPassValid(request.getPassword())) throw new AuthenticationException("Username is taken or password is invalid");
        user = userRepository.getUserAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("Invalid credentials");
        return user;
    }

    public User registerUser(NewUserRequest request){
        User user = request.takeUser();

        if(isNotDuplicateUsername(user.getUsername())){
            if (isUserValid(user.getUsername())){
                if(isPassValid(user.getPassword())){
                    user.setUserID(UUID.randomUUID().toString());
                    userRepository.saveUser(user.getUserID(),user.getUsername(),user.getPassword(),user.getEmail(),user.getFullname(),user.getAge());
                    logger.info(user.toString());
                } else throw new InvalidRequestException(" Invalid password. password must have at least 8 characters");
            }else throw new InvalidRequestException("Invalid username. username must have at least 8 - 20 characters");

        }else throw new ResourceConflictException("Username is already in use");
        return  user;
    }

    public List<User> getAllUsers(){
        return(List<User>) userRepository.findAll();
    }

    public User getUserHistory(Principal token){
        if(token.equals("")) return (User) userRepository.getAllHistory();
        return(User) userRepository.getUserHistory(token.getUsername());
    }

    private boolean isUserValid(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isPassValid(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    private boolean isNotDuplicateUsername(String username){
        return !userRepository.getAllUsernames().contains(username);
    }

}
