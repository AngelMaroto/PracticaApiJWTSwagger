package es.softtek.jwtDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoriaController {


	@GetMapping("categorias/{categoria}")
	public String findEquiposBycategoria(@PathVariable String categoria){
		return "Listado de la categoria "+categoria;
	}
}
