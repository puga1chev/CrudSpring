package ru.puga1chev.crudspring.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.puga1chev.crudspring.entity.*;
import ru.puga1chev.crudspring.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private ObjectService<User> userService;
    @Autowired
    private ObjectService<Role> roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView UsersViewPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users_view");

        List<User> users = userService.getAll("id");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_manage");

        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("action", "/admin/edit");
        modelAndView.addObject("roles", roleService.getAll("id"));

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_manage");

        modelAndView.addObject("user", new User());
        modelAndView.addObject("action", "/admin/add");
        modelAndView.addObject("roles", roleService.getAll("id"));

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");

        userService.delete(id);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUserPage(
            @RequestParam("username") String username,
            @RequestParam("login") String login,
            @RequestParam("pass") String pass,
            @RequestParam("roles") String[] pointedRoles,
            ModelMap model
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");

        // fill user object from form fields
        List<Role> roles = new ArrayList<>();
        for (String pointedRole: pointedRoles) {
            roles.add(roleService.getById(Long.parseLong(pointedRole)));
        }

        // if id set not null that will be error
        User newUser = new User(null, username, login, pass, roles);
        try {
            userService.insert(newUser);
        } catch (Exception e) {
            logger.error("Попытка записать пользователя", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUserPage(
            @RequestParam("id") String id,
            @RequestParam("username") String username,
            @RequestParam("login") String login,
            @RequestParam("pass") String pass,
            @RequestParam("roles") String[] pointedRoles,
            ModelMap model
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");

        // fill user object from form fields
        User modifiedUser = userService.getById(Long.parseLong(id));
        modifiedUser.setUsername(username);
        modifiedUser.setLogin(login);
        modifiedUser.setPass(pass);
        List<Role> roles = new ArrayList<>();
        for (String pointedRole: pointedRoles) {
            roles.add(roleService.getById(Long.parseLong(pointedRole)));
        }
        modifiedUser.setRoles(roles);
        try {
            userService.update(modifiedUser);
        } catch (Exception e) {
            logger.error("Попытка записать пользователя", e);
        }
        return modelAndView;
    }
}
