package pe.pugavalera.especial.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class BienvenidaController {

	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		return new ModelAndView("crud/Bienvenida", m);
	}
}