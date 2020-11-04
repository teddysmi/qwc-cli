package org.qwc.cli.tool.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.qwc.cli.tool.dao.UserEntity;
import org.qwc.cli.tool.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User createUser(User user) {

		// clear users before adding, limit to 1
		userRepository.deleteAll();

		UserEntity entity = new UserEntity();
		user.loadIntoEntity(entity);

		userRepository.saveAndFlush(entity);

		User newUser = new User();
		newUser.loadFromEntity(entity);

		return newUser;
	}

	@Transactional
	@Override
	public User getUser() {

		Optional<UserEntity> userOpt = userRepository.findAll().stream().findFirst();

		if (userOpt.isPresent()) {
			User user = new User();
			user.loadFromEntity(userOpt.get());

			return user;
		}

		return null;
	}

}
