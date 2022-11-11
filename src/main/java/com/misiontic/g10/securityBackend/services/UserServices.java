package com.misiontic.g10.securityBackend.services;

import com.misiontic.g10.securityBackend.models.User;
import com.misiontic.g10.securityBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
}
