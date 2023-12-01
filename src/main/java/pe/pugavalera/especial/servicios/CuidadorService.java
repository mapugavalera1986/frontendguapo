package pe.pugavalera.especial.servicios;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.pugavalera.especial.modelos.Cuidador;

@Service
public class CuidadorService {
	
	Logger logger = LoggerFactory.getLogger(CuidadorService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.superapi.baseUri}")
	private String baseUri;
	
	public List<Cuidador> listar(){
		List<Cuidador> temporal = new LinkedList<Cuidador>();
		try {
		ResponseEntity<List<Cuidador>> respuesta = restTemplate.exchange(baseUri+"apoderados",
				HttpMethod.GET, null ,new ParameterizedTypeReference<List<Cuidador>>() {});
			temporal = respuesta.getBody();
			logger.info("Lista de apoderados obtenida correctamente");
		} catch (Exception e) {
			logger.error("Error: "+ e);
		}
		return temporal;
	}
	
	public Cuidador ver(int id){
		Cuidador temporal = new Cuidador();
		try {
		ResponseEntity<Cuidador> respuesta = restTemplate.exchange(baseUri+"apoderados/"+id,
				HttpMethod.GET, null, new ParameterizedTypeReference<Cuidador>() {});
			temporal = respuesta.getBody();
			logger.info("Se encontró información del apoderado "+id);
		} catch (Exception e) {
			logger.error("Error: "+ e);
		}
		return temporal;
	}
	
	public void crear(Cuidador procesado) {
		try {
			restTemplate.postForEntity(baseUri + "apoderados", procesado,
					Cuidador.class);
			logger.info("Se logró registrar al apoderado");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}
	
	public void modificar(Cuidador procesado) {
		try {
			restTemplate.put(baseUri + "apoderados/"+procesado.getCuidadorId(), procesado);
			logger.info("Se logró modificar el apoderado");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}
	
	public void eliminar(int id) {
		restTemplate.delete(baseUri+"apoderados/"+id);
		logger.info("Cuidador y participantes eliminados con éxito");
	}
}