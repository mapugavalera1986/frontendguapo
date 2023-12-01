package pe.pugavalera.especial.modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Especialista {
	private int especialistaId;
	private String nmbrs;
	private String apllds;
	private String dni;
	private String ruc;
	private String correoE;
	private String telf;
	private String fechaIngreso;
}