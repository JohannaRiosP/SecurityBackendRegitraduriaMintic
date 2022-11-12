package com.misiontic.g10.securityBackend.services;

import com.misiontic.g10.securityBackend.models.User;
import com.misiontic.g10.securityBackend.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    /**
     * All users
     */
    public List<User> index(){
        return (List<User>)this.userRepository.findAll();

    }


    public Optional<User> show(int idUser){
        return this.userRepository.findById(idUser);
    }
    public User create(User newUser){
        if(newUser.getIdUser() != null){
            if(newUser.getEmail() != null && newUser.getPassword() != null){
                newUser.setPassword(this.convertToSHA256(newUser.getPassword()));
                return this.userRepository.save(newUser);
            }
            else {
                return newUser;
                }
        }
        else {
            return newUser;
        }
    }
    public User update(int idUser, User updatedUser){
        if(idUser > 0){
           Optional<User> tempUser = this.show(idUser);
           if(tempUser.isPresent()){
               if(updatedUser.getEmail() != null)
                   tempUser.get().setEmail(updatedUser.getEmail());
               if(updatedUser.getPassword() != null)
                   tempUser.get().setPassword(updatedUser.getPassword());
               if(updatedUser.getUserIdentify() != null)
                   tempUser.get().setUserIdentify(updatedUser.getUserIdentify());
               return this.userRepository.save(tempUser.get());
           }
           else{
               return updatedUser;
           }
        }
        else{
            return updatedUser;
        }
    }

        public boolean delete(int idUser){
        Boolean success = this.show(idUser).map(user -> {
            this.userRepository.delete(user);
            return true;
        }
        ).orElse(false);
        return success;
    }

    public HashMap<String, Boolean> login(User user){
        String email = user.getEmail();
        String password = this.convertToSHA256(user.getPassword());
        Optional<User> result = this.userRepository.login(email, password);
        HashMap<String, Boolean> response = new HashMap();
        if(result.isEmpty())
            response.put("login", false);
        else
            response.put("login", false);
        return response;

    }

    public String convertToSHA256(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
            }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
