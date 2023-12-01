package pe.pugavalera.especial.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class Usuario {
	private String username;
	private String password;
}
