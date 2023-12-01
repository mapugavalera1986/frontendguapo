package pe.pugavalera.especial.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pe.pugavalera.especial.modelos.Usuario;

@Service
public class BienvenidaService {
	private Logger logger = LoggerFactory.getLogger(BienvenidaService.class);
	
	@Value("${api.superapi.baseUri}")
	private String baseUri;
	
	public boolean ingreso(Usuario usuario) {
		if(usuario.getUsername().equals("admin") && usuario.getPassword().equals("admin")) {
			logger.info("Ingreso correcto");
			return true;
		} else {
			logger.error("Se necesita ingresar un nombre de usuario y contraseña válidos");
			return false;
		}
	}
}
