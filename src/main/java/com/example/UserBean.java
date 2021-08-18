package com.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

import com.example.dto.UserDTO;

import lombok.Data;

@Component(value = "userBean")
@SessionScope
@Data
public class UserBean {

	@Value("${url.getUsers}")
	private String urlGetUsers;

	@Value("${url.saveUser}")
	private String urlSaveUser;

	@Value("${url.deleteUser}")
	private String urlDeleteUser;

	@Autowired
	private RestTemplate restTemplate;

	private String firstName = "";
	private String lastName = "";

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBean.class);

	public void add() throws Exception {
		LOGGER.info("entered /saveUsers");
		this.restTemplate.postForObject(this.urlSaveUser,
				UserDTO.builder().firstName(this.firstName).lastName(this.lastName).birthDate(LocalDate.now()).build(),
				UserDTO.class);
		this.firstName = "";
		this.lastName = "";
	}

	public List<UserDTO> getUsers() throws Exception {
		LOGGER.info("entered /index");
		List<UserDTO> users = Arrays
				.asList(this.restTemplate.getForEntity(this.urlGetUsers, UserDTO[].class).getBody());
		LOGGER.info("users = {}", users);
		return users;
	}

	public void deleteUser(Long id) throws Exception {
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.urlDeleteUser + "/" + id, null,
				String.class);
		Optional<HttpStatus> status = Optional.ofNullable(response).map(ResponseEntity<String>::getStatusCode);
		if (!status.isPresent()) {
			throw new IllegalArgumentException(response.getBody());
		}
	}

}
