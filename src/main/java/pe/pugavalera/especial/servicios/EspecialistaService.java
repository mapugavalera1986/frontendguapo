package pe.pugavalera.especial.servicios;

import java.util.LinkedList;
import java.util.List;

import pe.pugavalera.especial.modelos.Especialista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EspecialistaService {

	Logger logger = LoggerFactory.getLogger(EspecialistaService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.superapi.baseUri}")
	private String baseUri;

	public List<Especialista> listar() {
		List<Especialista> temporal = new LinkedList<Especialista>();
		try {
			ResponseEntity<List<Especialista>> respuesta = restTemplate.exchange(baseUri + "especialistas",
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Especialista>>() {
					});
			temporal = respuesta.getBody();
			logger.info("Lista de especialistas obtenida correctamente");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return temporal;
	}

	public Especialista ver(int id) {
		Especialista temporal = new Especialista();
		try {
			ResponseEntity<Especialista> respuesta = restTemplate.exchange(baseUri + "especialistas/" + id,
					HttpMethod.GET, null, new ParameterizedTypeReference<Especialista>() {
					});
			temporal = respuesta.getBody();
			logger.info("Se encontró información del terapeuta " + id);
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return temporal;
	}

	public void crear(Especialista procesado) {
		try {
			System.out.println(procesado.toString());
			restTemplate.postForObject(baseUri + "especialistas", procesado,
					Especialista.class);
			logger.info("Se logró registrar al terapeuta");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}
	
	public void modificar(Especialista procesado) {
		try {
			restTemplate.put(baseUri + "especialistas/"+procesado.getEspecialistaId(), procesado);
			logger.info("Se logró modificar al terapeuta");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}

	public void eliminar(int id) {
		restTemplate.delete(baseUri + "especialistas/" + id);
		logger.info("Participante eliminado con éxito");
	}
}