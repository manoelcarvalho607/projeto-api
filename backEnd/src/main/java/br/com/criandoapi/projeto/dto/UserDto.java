/**
 * 
 */
package br.com.criandoapi.projeto.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * manoel.carvalho
 *
 */

@Getter
@Setter
public class UserDto {
	private String nome;
	private String email;
	private String senha;
	
	
	public UserDto(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	
}
