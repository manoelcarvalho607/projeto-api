/**
 * 
 */
package br.com.criandoapi.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * manoel.carvalho
 *
 */

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "O nome é obrigatório!")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres!")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@Email(message = "Insira um email válido!")
	@NotBlank(message = "O email é obrigatório!")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@NotBlank(message = "O telefone é obrigatório!")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	@NotBlank(message = "A senha é obrigatório!")
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;

}
