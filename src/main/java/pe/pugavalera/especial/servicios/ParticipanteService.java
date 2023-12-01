package pe.pugavalera.especial.servicios;

import java.util.LinkedList;
import java.util.List;

import pe.pugavalera.especial.modelos.Participante;
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
public class ParticipanteService {
	
	Logger logger = LoggerFactory.getLogger(ParticipanteService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.superapi.baseUri}")
	private String baseUri;
	
	public List<Participante> listar(){
		List<Participante> temporal = new LinkedList<Participante>();
		try {
		ResponseEntity<List<Participante>> respuesta = restTemplate.exchange(baseUri+"inscritos",
				HttpMethod.GET, null ,new ParameterizedTypeReference<List<Participante>>() {});
			temporal = respuesta.getBody();
			logger.info("Lista de participantes obtenida correctamente");
		} catch (Exception e) {
			logger.error("Error: "+ e);
		}
		return temporal;
	}
	
	public Participante ver(int id){
		Participante temporal = new Participante();
		try {
			ResponseEntity<Participante> respuesta = restTemplate.exchange(baseUri+"inscritos/"+id,
				HttpMethod.GET, null, new ParameterizedTypeReference<Participante>() {});
			temporal = respuesta.getBody();
			logger.info("Se encontró información del participante "+id);
		} catch (Exception e) {
			logger.error("Error: "+ e);
		}
		return temporal;
	}

	
	public void crear(Participante procesado) {
		try {
			restTemplate.postForEntity(baseUri + "inscritos", procesado,
					Participante.class);
			logger.info("Se logró registrar al participante");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}
	
	public void modificar(Participante procesado) {
		try {
			restTemplate.put(baseUri + "inscritos/"+procesado.getParticipanteId(), procesado);
			logger.info("Se logró modificar al participante");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}
	
	public void eliminar(int id) {
		restTemplate.delete(baseUri+"inscritos/"+id);
		logger.info("Participante eliminado con éxito");
	}
}