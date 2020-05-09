package mova.laboratorio.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Vacante;

@Service
public class VacantesServiceImpl implements IVacanteService{

	private List<Vacante> listaEmpleos;
	
	/*
	 * Constructor General
	 */
	public VacantesServiceImpl() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-YYYY");
		listaEmpleos = new LinkedList<>();
		
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero de Informatica");
			vacante1.setDescripcion("Puesto encargado de llevar a cabo el diseno y arquitectura de software");
			vacante1.setFecha(formatoFecha.parse("10-11-2019"));
			vacante1.setSalario(75000.00);
			vacante1.setDestacado(1);
			vacante1.setEstatus("Creada");
			vacante1.setImagen("empresa1.png");
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ayudante Contable");
			vacante2.setDescripcion("Encargado de llevar los libros contables en apoyo a las areas contables");
			vacante2.setFecha(formatoFecha.parse("09-02-2017"));
			vacante2.setSalario(38300.25);
			vacante2.setDestacado(0);
			vacante2.setEstatus("Aprobada");
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Panadero");
			vacante3.setDescripcion("Se requiere Panadero de estilo frances para hacer pancitos");
			vacante3.setFecha(formatoFecha.parse("30-03-2017"));
			vacante3.setSalario(46465.00);
			vacante3.setEstatus("Aprobada");
			vacante3.setDestacado(0);
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Ebanista");
			vacante4.setDescripcion("Se requiere carpintero especialidazo para la creacion de muebles finos");
			vacante4.setFecha(formatoFecha.parse("20-02-2017"));
			vacante4.setSalario(46465.00);
			vacante4.setDestacado(1);
			vacante4.setEstatus("Creada");
			vacante4.setImagen("empresa3.png");
			
			listaEmpleos.add(vacante1);
			listaEmpleos.add(vacante2);
			listaEmpleos.add(vacante3);
			listaEmpleos.add(vacante4);
			
		}
		catch(ParseException ex) {
			System.out.println("Error de Coversion de Formato de fecha: "+ex.getMessage());
		}

	}

	@Override
	public List<Vacante> buscarTodas() {
		return listaEmpleos;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for (Vacante vacante : listaEmpleos) {
			if (vacante.getId() == idVacante) {
				return vacante;
			}
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		listaEmpleos.add(vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarVacante(Integer idVacante) {
		// TODO Auto-generated method stub
		
	}

}
