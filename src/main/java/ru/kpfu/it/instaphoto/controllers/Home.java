package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.it.instaphoto.models.User;
import ru.kpfu.it.instaphoto.services.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:46 PM
 */
@Controller
@RequestMapping("/")
public class Home {
    @Autowired
    private UserRepository userRepository;
    private static SecureRandom random = new SecureRandom();

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model m){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model m){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("login") String login, @RequestParam("password") String password,Model m, HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user=userRepository.findByLogin(login);
        if(user==null){
            return "redirect:/";
        }

        byte[] salt = user.getSalt();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        if(!Arrays.equals(hash, user.getHash())) {
            return "redirect:/";
        }

        session.setAttribute("user", user);
        return "redirect:feed";
    }
}
