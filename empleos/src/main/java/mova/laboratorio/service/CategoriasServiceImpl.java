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
		
		Categoria categoria2 = new Categoria();
		categoria2.setId(2);
		categoria2.setNombre("Ventas");
		categoria2.setDescripcion("Ofertas de trabajo relacionado con ventas");
		
		Categoria categoria3 = new Categoria();
		categoria3.setId(3);
		categoria3.setNombre("Arquitectura");
		categoria3.setDescripcion("Diseño de planos en general y trabajos relacionados");
		
		Categoria categoria4 = new Categoria();
		categoria4.setId(4);
		categoria4.setNombre("Informatica");
		categoria4.setDescripcion("Trabajos relacionado con la carrera Informatica");
		
		listadoCategorias.add(categoria1);
		listadoCategorias.add(categoria2);
		listadoCategorias.add(categoria3);
		listadoCategorias.add(categoria4);
	}
	
	@Override
	public void guardarCategoria(Categoria categoria) {
		//TODO: BORRAR el ADD ID
		int cantidad = listadoCategorias.size();
		cantidad++;
		categoria.setId(cantidad);
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
