package br.appLogin.appLogin.controller;
import br.appLogin.appLogin.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "cadastro";
    }

//    @RequestMapping(value = "cadastroUsuario", method = RequestMethod.POST) {
//        public String cadastroUsuario() {
//
//          EM PROD PAPAPITOW.
//
//            return "";
//        }
//    }


}
