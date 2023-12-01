package pe.pugavalera.especial.controladores;

import java.util.List;

import pe.pugavalera.especial.modelos.Cita;
import pe.pugavalera.especial.servicios.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/citas")
public class CitaController {
	
	@Autowired
	private CitaService srvc_citas;
		
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Cita> listar = srvc_citas.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Citas", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Cita ver = new Cita();
		if(id>0) {
			ver = srvc_citas.ver(id);
		}
		m.addAttribute("cita", ver);
		return new ModelAndView("crud/crear/Cita", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Cita procesar, ModelMap m) {
		if(procesar.getCitaId() == 0) {
			srvc_citas.crear(procesar);
		}else {
			srvc_citas.modificar(procesar);
		}
		return new ModelAndView("redirect:/apoderados");
	}
	
	@GetMapping("{id}/eliminar")
	public ModelAndView solicitarEliminar(@PathVariable("id") int id, ModelMap m) {
		Cita ver = srvc_citas.ver(id);
		m.addAttribute("cita", ver);
		return new ModelAndView("crud/eliminar/Cita", m);
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminar(Cita eliminar, ModelMap m) {
		srvc_citas.eliminar(eliminar.getCitaId());
		return new ModelAndView("redirect:/apoderados", m);
	}
	
	@PostMapping("/volver")
	public ModelAndView volver(ModelMap m) {
		return new ModelAndView("redirect:/apoderados");
	}
}