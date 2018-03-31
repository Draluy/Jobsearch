package fr.raluy.jobsearchback.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by draluy on 19/10/2017.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/registration")
    @ResponseBody
    public String registration() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    @PostMapping(value = "/registration")
    public ResponseEntity createNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        ResponseEntity<Object> result = new ResponseEntity<>(HttpStatus.OK);
        if (user.getEmail() == null || user.getPassword() == null || user.getPasswordVerification() == null) {
            result = new ResponseEntity<>("Username or password is empty", HttpStatus.BAD_REQUEST);
        } else if (!Objects.equals(user.getPassword(), user.getPasswordVerification())) {
            result = new ResponseEntity<>("Passwords are not the same", HttpStatus.BAD_REQUEST);
        } else if (bindingResult.hasErrors()) {
            final StringBuilder strb = new StringBuilder("Other errors: ");
            bindingResult.getAllErrors().stream().forEach(objectError -> {
                strb.append(objectError.getDefaultMessage());
            });
            result = new ResponseEntity<>(strb.toString(), HttpStatus.BAD_REQUEST);
        }

        if (HttpStatus.OK.equals(result.getStatusCode())) {
            User userExists = userService.findUserByEmail(user.getEmail());
            if (userExists != null) {
                result = new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        }

        if (HttpStatus.OK.equals(result.getStatusCode())) {
            userService.saveUser(user);
        }

        return result;
    }
}
