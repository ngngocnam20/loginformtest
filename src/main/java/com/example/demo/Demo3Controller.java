package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Demo3Controller {

    @Autowired
    private UserRepository repository;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String viewSignUpPage(Model model)
    {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @GetMapping("/login_success")
    public String loginSuccessPage()
    {
        return "login_success";
    }

    @PostMapping("/process_register")
    public String processingRegistration(User user)
    {
        System.out.println("Email: " + user.getEmail() + "\nPassword: " + user.getPassword() + "\nTen: " + user.getFirstName() + "\nHo: " + user.getLastName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);

        return "register_success";
    }

}