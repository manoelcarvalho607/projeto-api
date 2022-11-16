/**
 * 
 */
package br.com.criandoapi.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.criandoapi.projeto.domain.model.Users;
import br.com.criandoapi.projeto.dto.UserDto;
import br.com.criandoapi.projeto.repository.IUsersRepository;
import br.com.criandoapi.projeto.security.Token;
import br.com.criandoapi.projeto.security.TokenUtil;
import lombok.AllArgsConstructor;

/**
 * manoel.carvalho
 *
 */


@Service
public class UserService {

	private IUsersRepository usersRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public List<Users> listUser() {
		List<Users> list = usersRepository.findAll();
		return list;
	}
	
	public Optional<Users> readUserId(Long userId) {
		Optional<Users> read = usersRepository.findById(userId);
		return read;
	}
	
	public Users createUser(Users users) {
		String encoder = this.passwordEncoder.encode(users.getSenha());
		users.setSenha(encoder);
		Users newUser = usersRepository.save(users);
		return newUser;
	}
	
	public boolean updateUser(Long userId, Users users) {
		String encoder = this.passwordEncoder.encode(users.getSenha());
		users.setSenha(encoder);
		boolean newUser = usersRepository.existsById(userId);
		return newUser;
		
	}
	
	public boolean deleteId(Long userId) {
		 boolean deleteUser = usersRepository.existsById(userId);
		return deleteUser;
	}
	
	public Users delete(Long userId) {
		  usersRepository.deleteById(userId);
		return null;
	}

	
	@SuppressWarnings("deprecation")
	public boolean validPassword(Users user) {
		String senha = usersRepository.getById(user.getId()).getSenha();
		boolean valid = passwordEncoder.matches(user.getSenha(), senha);
		return valid;
	}

	public Token createToken(@Valid UserDto user) {
		
		Users user1 =  usersRepository.findBynomeOrEmail(user.getNome(), user.getEmail());
		if(user1 != null) {
			boolean valid = passwordEncoder.matches(user.getSenha(), user1.getSenha());
			if(valid) {
				return new Token(TokenUtil.createToken(user1));
			}
		}
		
		
		return null;
	}

	
}
