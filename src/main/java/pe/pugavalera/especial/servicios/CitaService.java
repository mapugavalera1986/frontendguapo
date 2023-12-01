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

import pe.pugavalera.especial.modelos.Cita;

@Service
public class CitaService {

	Logger logger = LoggerFactory.getLogger(CuidadorService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.superapi.baseUri}")
	private String baseUri;

	public List<Cita> listar() {
		List<Cita> temporal = new LinkedList<Cita>();
		try {
			ResponseEntity<List<Cita>> respuesta = restTemplate.exchange(baseUri + "citas", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Cita>>() {
					});
			temporal = respuesta.getBody();
			logger.info("Lista de apoderados obtenida correctamente");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return temporal;
	}

	public Cita ver(int id) {
		Cita temporal = new Cita();
		try {
			ResponseEntity<Cita> respuesta = restTemplate.exchange(baseUri + "citas/" + id, HttpMethod.GET,
					null, new ParameterizedTypeReference<Cita>() {
					});
			temporal = respuesta.getBody();
			logger.info("Se encontró información de la cita " + id);
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return temporal;
	}

	public void crear(Cita procesado) {
		try {
			restTemplate.postForEntity(baseUri + "citas", procesado, Cita.class);
			logger.info("Se logró agendar la cita");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}

	public void modificar(Cita procesado) {
		try {
			restTemplate.put(baseUri + "citas/" + procesado.getCitaId(), procesado);
			logger.info("Se logró modificar la cita");
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}

	public void eliminar(int id) {
		restTemplate.delete(baseUri + "citas/" + id);
		logger.info("Cita eliminada con éxito");
	}
}
