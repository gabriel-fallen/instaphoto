package ru.kpfu.it.instaphoto.services;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.it.instaphoto.models.Photo;
import ru.kpfu.it.instaphoto.models.User;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:35 PM
 */
public interface PhotoRepository extends CrudRepository<Photo,Long> {
    public Collection<Photo> findAllByAuthor(User author);
}
