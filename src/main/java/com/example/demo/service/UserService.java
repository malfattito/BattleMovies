package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.BattleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BattleRepository battleRepository;
    
    public UserDTO createUser(String login, String senha) {

        User user =  userRepository.save(new User(login, senha, 0L));
        return createUserDTOByUser(user, user.getPoints());

    }

    public List<UserDTO> ranking() {

        List<User> users = userRepository.findUserOrderByPoints();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user: users) {
            Long points = Long.valueOf(user.getPoints());
            Long countBatlle = battleRepository.countBattleByUser(user);
            Long countBatlleWin = battleRepository.countBattleWinByUser(user);

            points = points * ((countBatlleWin * 100) / countBatlle);
            userDTOS.add(createUserDTOByUser(user, points));

        }

        Collections.sort(users);

        return userDTOS;
    }

    private UserDTO createUserDTOByUser(User user, Long pointsPercent) {
        return new UserDTO(user.getId(), user.getLogin(), pointsPercent);
    }
}
