package pe.pugavalera.especial.controladores;

import java.util.List;

import pe.pugavalera.especial.modelos.Especialista;
import pe.pugavalera.especial.servicios.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/especialistas")
public class EspecialistaController {
		
	@Autowired
	private EspecialistaService srvc_especialistas;
	
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Especialista> listar = srvc_especialistas.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Especialistas", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Especialista ver = new Especialista();
		if(id>0) {
			ver = srvc_especialistas.ver(id);
		}
		m.addAttribute("especialista", ver);
		return new ModelAndView("crud/crear/Especialista", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Especialista procesar, ModelMap m) {
		if(procesar.getEspecialistaId() == 0) {
			srvc_especialistas.crear(procesar);
		}else {
			srvc_especialistas.modificar(procesar);
		}
		return new ModelAndView("redirect:/especialistas");
	}
	
	@GetMapping("{id}/eliminar")
	public ModelAndView solicitarEliminar(@PathVariable("id") int id, ModelMap m) {
		Especialista ver = srvc_especialistas.ver(id);
		m.addAttribute("especialista", ver);
		return new ModelAndView("crud/eliminar/Especialista", m);
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminar(Especialista eliminar, ModelMap m) {
		srvc_especialistas.eliminar(eliminar.getEspecialistaId());
		return new ModelAndView("redirect:/especialistas", m);
	}
	
	@PostMapping("/volver")
	public ModelAndView volver(ModelMap m) {
		return new ModelAndView("redirect:/especialistas");
	}
}