package ru.puga1chev.crudspring.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.puga1chev.crudspring.entity.*;
import ru.puga1chev.crudspring.service.*;

import java.util.List;

@Controller
public class MainController {


    private final static Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private ObjectService<User> userService;
    @Autowired
    private ObjectService<Role> roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ModelAndView UsersViewPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users_view");

        List<User> users = userService.getAll("id");
        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
