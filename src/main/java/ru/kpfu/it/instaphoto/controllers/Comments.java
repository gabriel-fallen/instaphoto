package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.it.instaphoto.models.Comment;
import ru.kpfu.it.instaphoto.models.Photo;
import ru.kpfu.it.instaphoto.models.User;
import ru.kpfu.it.instaphoto.services.CommentRepository;
import ru.kpfu.it.instaphoto.services.PhotoRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 5/5/14
 *         Time: 6:39 PM
 */
@Controller
@RequestMapping("/comments")
public class Comments {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Comment> getComments(@PathVariable("id") Long id) {
        final Photo photo = photoRepository.findOne(id);
        if (photo == null)
            return Collections.EMPTY_LIST;

        return photo.getComments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String addComment(@PathVariable("id") Long id, Comment comment, HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null)
            return "Error: no user is logged in.";

        final Photo photo = photoRepository.findOne(id);
        if (photo == null)
            return "Error: unknown photo.";

        comment.setAuthor(user);
        comment.setPhoto(photo);
        commentRepository.save(comment);
        return "ok";
    }
}



