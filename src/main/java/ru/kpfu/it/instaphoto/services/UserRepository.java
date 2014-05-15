package ru.kpfu.it.instaphoto.services;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.it.instaphoto.models.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:27 PM
 */
public interface UserRepository extends CrudRepository<User,Long>{
    User findByLogin(String login);
}
