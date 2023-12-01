package pe.pugavalera.especial.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
	private int citaId;
	private String fecha;
	private int participanteId;
	private int especialistaId;
	private String modalidad;
	private String estado;
}
