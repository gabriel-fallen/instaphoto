package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.it.instaphoto.models.Photo;
import ru.kpfu.it.instaphoto.models.User;
import ru.kpfu.it.instaphoto.services.PhotoRepository;
import ru.kpfu.it.instaphoto.services.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 5/5/14
 *         Time: 6:12 PM
 */
@Controller
@RequestMapping("/feed")
public class Feed {
    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getFeed(Model model, HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/";

        Collection<Photo> photos = photoRepository.findAllByAuthor(user);
        model.addAttribute("photos", photos);
        return "feed";
    }
}
