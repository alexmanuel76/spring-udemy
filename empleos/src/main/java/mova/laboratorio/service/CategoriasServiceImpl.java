package mova.laboratorio.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService {

	private List<Categoria> listadoCategorias;

	
	public CategoriasServiceImpl() {
		listadoCategorias = new LinkedList<>();
		Categoria categoria1 = new Categoria();
		categoria1.setId(1);
		categoria1.setNombre("Recursos Humanos");
		categoria1.setDescripcion("Trabajos relacionados con el area de RH");
		
		Categoria categoria2 = new Categoria(2,"Ventas","Ofertas de trabajo relacionado con ventas");
		Categoria categoria3 = new Categoria(3,"Arquitectura","Diseño de planos en general y trabajos relacionados");
		Categoria categoria4 = new Categoria(4,"Informatica","Trabajos relacionado con la carrera Informatica");
		
		listadoCategorias.add(categoria1);
		listadoCategorias.add(categoria2);
		listadoCategorias.add(categoria3);
		listadoCategorias.add(categoria4);
	}
	
	@Override
	public void guardarCategoria(Categoria categoria) {
		listadoCategorias.add(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return listadoCategorias;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria categoria : listadoCategorias) {
			if (categoria.getId().equals(idCategoria)){
				return categoria;
			}
		}
		return null;
	}

}
