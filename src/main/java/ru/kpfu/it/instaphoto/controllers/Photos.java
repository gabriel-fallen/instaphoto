package ru.kpfu.it.instaphoto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.it.instaphoto.models.Photo;
import ru.kpfu.it.instaphoto.models.User;
import ru.kpfu.it.instaphoto.services.PhotoRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 5/5/14
 *         Time: 4:57 PM
 */
@Controller
@RequestMapping("/photos")
public class Photos {
    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody byte[] getPhoto(@PathVariable("id") Long id, HttpServletResponse response) {
        final Photo photo = photoRepository.findOne(id);
        if (photo == null) {
            // return 404
            return null;
        }

//        DataBuffer buffer = new DataBufferByte(photo.getData(), photo.getData().length);
//        SampleModel sm = new SinglePixelPackedSampleModel();
//        WritableRaster wr = new WritableRaster();
        Image img = new ImageIcon(photo.getData()).getImage();
        response.setContentType("image/png");
        return photo.getData();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPhoto(@RequestParam("data") MultipartFile file, HttpSession session) throws IOException {
        final User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/";

        final Photo photo = new Photo();
        photo.setAuthor(user);
        photo.setData(file.getBytes());
        photoRepository.save(photo);
        //user.getPhotos().add(photo);

        return "redirect:feed";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String newPhoto(Model model) {
        model.addAttribute("photo", new Photo());
        return "photo";
    }
}
