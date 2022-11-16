

package br.com.criandoapi.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto.domain.model.Users;
import br.com.criandoapi.projeto.dto.UserDto;
import br.com.criandoapi.projeto.security.Token;
import br.com.criandoapi.projeto.service.UserService;
import lombok.AllArgsConstructor;

/**
 * manoel.carvalho
 *
 */

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	
	
	
	
	private UserService userService;
	
	

	
	@GetMapping
	public ResponseEntity<List<Users>> list() {
		 return ResponseEntity.status(200).body(userService.listUser());
	}
	
	@GetMapping("/{usersId}")
	public ResponseEntity<Users> read(@PathVariable Long usersId) {
		return userService.readUserId(usersId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Users create(@Valid @RequestBody Users users) {
		return userService.createUser(users);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Users> update(@Valid @PathVariable Long userId, @RequestBody Users users) {
		if (!userService.updateUser(userId, users)) {
			return ResponseEntity.notFound().build();
		}
		users.setId(userId);
		users = userService.createUser(users);
		return ResponseEntity.ok(users);
	}
	
	@DeleteMapping("/{usersId}")
	public ResponseEntity<Void> delete(@PathVariable Long usersId) {
		if (!userService.deleteId(usersId)) {
			return ResponseEntity.notFound().build();
		}
		userService.delete(usersId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Token> logar(@Valid @RequestBody UserDto user) {
		Token token = userService.createToken(user);
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(403).build();
		
		}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
		
	}

}
