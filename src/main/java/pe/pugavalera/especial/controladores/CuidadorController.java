package pe.pugavalera.especial.controladores;

import java.util.List;

import pe.pugavalera.especial.modelos.Cuidador;
import pe.pugavalera.especial.servicios.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/apoderados")
public class CuidadorController {
	
	@Autowired
	private CuidadorService srvc_cuidadores;
		
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Cuidador> listar = srvc_cuidadores.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Cuidadores", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Cuidador ver = new Cuidador();
		if(id>0) {
			ver = srvc_cuidadores.ver(id);
		}
		m.addAttribute("cuidador", ver);
		return new ModelAndView("crud/crear/Cuidador", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Cuidador procesar, ModelMap m) {
		if(procesar.getCuidadorId() == 0) {
			srvc_cuidadores.crear(procesar);
		}else {
			srvc_cuidadores.modificar(procesar);
		}
		return new ModelAndView("redirect:/apoderados");
	}
	
	@GetMapping("{id}/eliminar")
	public ModelAndView solicitarEliminar(@PathVariable("id") int id, ModelMap m) {
		Cuidador ver = srvc_cuidadores.ver(id);
		m.addAttribute("cuidador", ver);
		return new ModelAndView("crud/eliminar/Cuidador", m);
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminar(Cuidador eliminar, ModelMap m) {
		srvc_cuidadores.eliminar(eliminar.getCuidadorId());
		return new ModelAndView("redirect:/apoderados", m);
	}
	
	@PostMapping("/volver")
	public ModelAndView volver(ModelMap m) {
		return new ModelAndView("redirect:/apoderados");
	}
}