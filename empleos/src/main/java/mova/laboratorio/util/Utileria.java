package mova.laboratorio.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	/**
	 * Metodo para Guardar un archivo en Disco
	 * 
	 * @param multipart Archivo proveniente del servidor
	 * @param ruta      ruta en donde guardar el archivo
	 * @return Nombre final del Archivo
	 */
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Ontenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		String prefijo = randomAlphaNumeric(8);
		nombreOriginal = nombreOriginal.replace(" ", "-");
		String nombreFinal = prefijo + nombreOriginal;
		try {
			File imageFile = new File(ruta + nombreFinal);
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException ex) {
			System.out.println("Error al tratar de grabar el archivo: " + ex.getMessage());
			return null;
		}
	}
	
	/**
	 * Metodo que devuelve una cadena de numero y letras aleatorias
	 * @param count Cantidad de Caracteres
	 * @return Cadena alfanumerica
	 */
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int caracter = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(caracter));
		}
		return builder.toString();
	}

}
