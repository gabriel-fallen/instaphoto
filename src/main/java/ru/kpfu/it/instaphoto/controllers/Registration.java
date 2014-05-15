package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 2:52 PM
 */
@Controller
@RequestMapping("/register")
public class Registration {

    @Autowired
    private UserRepository userRepository;
    private static SecureRandom random = new SecureRandom();

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@ModelAttribute("user") User user, Model model){
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(HttpSession session, User user, Model model, BindingResult result) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "redirect:register";
        }

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();

        user.setSalt(salt);
        user.setHash(hash);
        user.setPassword("");

        session.setAttribute("user", user);
        userRepository.save(user);
        return "redirect:feed";
    }

}
