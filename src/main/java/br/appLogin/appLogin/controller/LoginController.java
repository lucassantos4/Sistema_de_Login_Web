package br.appLogin.appLogin.controller;
import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {
    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    @GetMapping("/home")
    public String workspace(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null){
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuarioLogado);
        return "home";
    }

    @PostMapping("/login")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response, HttpSession session ) throws UnsupportedEncodingException {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if(usuarioLogado != null) {
            session.setAttribute("usuarioLogado", usuarioLogado);
            CookieService.setCookie(response, "UsuarioId", String.valueOf(usuarioLogado.getId()), 10000 );
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuarioLogado.getNome()), 10000 );
            return "redirect:/home";

        }
        model.addAttribute("erro","ERRO! Usuário Inválido!");
        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/cadastroUsuario";
        }
        ur.save(usuario);
        return "redirect:/login";
    }

}


