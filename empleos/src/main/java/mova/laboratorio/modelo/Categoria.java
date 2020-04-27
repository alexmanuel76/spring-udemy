package mova.laboratorio.modelo;

public class Categoria {
	
	private Integer id;
	private String nombre;
	private String descripcion;
	
	/**
	 * Constructor General de la Categoria
	 * @param id Id de la Categoria
	 * @param nombre Nombre de la Categoria
	 * @param descripcion Descripcion de la categoria
	 */
	public Categoria(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
	public Categoria() {
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
