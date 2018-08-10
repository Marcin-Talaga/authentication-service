package com.epam.authenticationservice.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.authenticationservice.entity.User;

@Component
public class UserValidator {
	@Value("${users.file}")
	private String usersFile;

	public Optional<User> checkIfUserIsValid(User user) {

		try {
			Properties properties = new Properties();
			InputStream is = Files.newInputStream(Paths.get(usersFile));
			properties.load(is);
			is.close();
			return properties.entrySet().stream().map(u -> new User(u.getKey().toString(), u.getValue().toString()))
					.filter((u) -> Objects.equals(u, user)).findFirst();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}
}
