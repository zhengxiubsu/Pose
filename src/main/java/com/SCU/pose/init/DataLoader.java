package com.SCU.pose.init;

import com.SCU.pose.model.User;
import com.SCU.pose.model.ExerciseLog;
import com.SCU.pose.service.UserService;
import com.SCU.pose.service.ExerciseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final ExerciseLogService exerciseLogService;

    @Autowired
    public DataLoader(UserService userService, ExerciseLogService exerciseLogService) {
        this.userService = userService;
        this.exerciseLogService = exerciseLogService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 创建表的逻辑已经由 JPA 处理，所以这里只处理数据插入
        insertUsers();
        insertExerciseLogs();
        // 其他数据操作...
    }

    private void insertUsers() {
        for (int i = 1; i <= 10; i++) {
            User user = new User("user" + i, "password" + i, "user" + i + "@example.com", 20 + i, "male");
            userService.saveUser(user);
        }
    }

    private void insertExerciseLogs() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            User user = userService.getUserById(i).get();
            ExerciseLog log = new ExerciseLog(user.getId(), "push-ups", random.nextInt(50), random.nextInt(180), new Timestamp(System.currentTimeMillis()));
            exerciseLogService.saveExerciseLog(log);
        }
    }
}
