package com.codetreatise.service;

import com.codetreatise.model.User;
import com.codetreatise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, CrudService<User>
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User entity)
    {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity)
    {
        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity)
    {
        userRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public boolean authenticate(String username, String password)
    {
        User user = this.findByEmail(username);
        if (user == null)
        {
            return false;
        } else
        {
            return password.equals(user.getPassword());
        }
    }

    @Override
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteInBatch(List<User> users)
    {
        userRepository.deleteInBatch(users);
    }

}
