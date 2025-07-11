package fr.limayrac.pfeback;

import fr.limayrac.pfeback.dto.LoginRequest;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String doLogin(LoginRequest request) {
        User user = userRepository.findByLogin(request.getLogin());

        if (user != null) {
            return "User details found";
        }

        return "User details not found";
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
