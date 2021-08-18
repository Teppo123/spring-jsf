package com.example;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.dto.UserDTO;
import com.google.common.collect.Lists;

import lombok.Data;

@Component(value = "userBean")
@SessionScope
@Data
public class UserBean {

//	@Inject
//	private UserWrapperService userService;

	private String firstName = "";
	private String lastName = "";

	public void add() throws Exception {
//		this.userService.saveUser(new UserDTO(null, this.firstName, this.lastName, LocalDate.now(), null));
		// TODO FIXME
		this.firstName = "";
		this.lastName = "";
	}

	// implicit getter/setter declarations since lombok annotations don't work that
	// well for xhmtl calls

//	public String getFirstName() {
//		return this.firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return this.lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
	public List<UserDTO> getUsers() throws Exception {
		// TODO FIXME
		return Lists.newArrayList(UserDTO.builder().firstName("Jaakko").build());
//		return this.userService.getUsers();
	}

	public void deleteUser(Long id) throws Exception {
		// TODO FIXME
//		this.userService.deleteUserById(id);
	}

}
