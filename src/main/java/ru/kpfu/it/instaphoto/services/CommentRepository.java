package ru.kpfu.it.instaphoto.services;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.it.instaphoto.models.Comment;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:37 PM
 */
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
