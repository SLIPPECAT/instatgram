package project.instatgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @CrossOrigin
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
        
    }
}
