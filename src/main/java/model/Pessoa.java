package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pessoa {

	private int id;
	private String nome;
	private String telefoneFixo;
	private String telefoneCelular;
	
}
