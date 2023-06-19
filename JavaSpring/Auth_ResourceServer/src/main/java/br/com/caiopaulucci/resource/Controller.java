package br.com.caiopaulucci.resource;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    }
}