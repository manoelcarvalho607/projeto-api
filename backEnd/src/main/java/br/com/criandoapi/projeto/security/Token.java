/**
 * 
 */
package br.com.criandoapi.projeto.security;

import lombok.Getter;
import lombok.Setter;

/**
 * manoel.carvalho
 *
 */

@Getter
@Setter
public class Token {
	private String token;
	
	public Token(String token) {
		super();
		this.token = token;
	}
}
