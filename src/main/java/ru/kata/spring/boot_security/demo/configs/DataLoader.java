package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        if (userService.findByEmail("admin@admin.com").isEmpty()) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));
            Set<Role> roleHashSetAdmin = new HashSet<>(roleService.getAllRoles());
            Set<Role> roleHashSetUser = new HashSet<>();
            roleHashSetUser.add(roleService.getRoleById(2));
            userService.saveUser(new User("admin", "admin", 20, "admin@admin.com",
                    "admin", roleHashSetAdmin));
            userService.saveUser(new User("user1", "user1", 12, "user1@user1.com",
                    "user1", roleHashSetUser));
        }
    }
}
