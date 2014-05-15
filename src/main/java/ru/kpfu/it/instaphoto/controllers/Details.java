package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.it.instaphoto.models.Photo;
import ru.kpfu.it.instaphoto.services.PhotoRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 5/5/14
 *         Time: 6:32 PM
 */
@Controller
@RequestMapping("/details")
public class Details {
    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Long id, Model model) {
        final Photo photo = photoRepository.findOne(id);
        if (photo == null) {
            // FIXME: return 404?
            return "redirect:feed";
        }

        model.addAttribute("photo", photo);
        return "details";
    }
}



