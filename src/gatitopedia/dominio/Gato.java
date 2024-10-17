/*
 * Paradigmas de Programaciï¿½n
 * Josï¿½ Enrique Gonzï¿½lez Sï¿½nchez
 * Grupo 412
 * 20-06-2024
 * Reto 3
 */

package gatitopedia.dominio;

import java.util.ArrayList;
import java.util.Date;

public class Gato {
	private int edadGato; // Número libre
	private float pesoGato;
	private float precioAdopcion; // Número con rango
	private String nombreGato; // Texto en formato libre
	private String identificadorGato; // Texto con formato predefinido
	private Date fechaNacimiento; // Fecha
	private String sexoGato; // Dato mutuamente excluyente fijo: "Macho" o "Hembra"
	private boolean gatoEsterilizado; // Dato mutuamente excluyente fijo: "Sí" o "No"
	private String personalidad; // Dato mutuamente excluyente dinámico
	/*
	 * Dato multivalorado no excluyentes fijas "habitosEntrenamiento" Uso de caja de
	 * arena, Uso de rascador, Caminar con correa, Obedecer ordenes.
	 */
	private ArrayList<String> habitosEntrenamiento;
	private ArrayList<String> colorGato; // Dato multivalorado no excluyente dinámico
	private String rutaImagen;

	public Gato() {
		// Valores por defecto para las variables miembro
		this.edadGato = 0;
		this.pesoGato = 0.0f;
		this.precioAdopcion = 0.0f;
		this.nombreGato = "";
		this.identificadorGato = "";
		this.fechaNacimiento = null;
		this.sexoGato = "";
		this.gatoEsterilizado = false;
		this.personalidad = "";
		this.habitosEntrenamiento = null;
		this.colorGato = null;
		this.rutaImagen = "";
	}

	// Edad Gato
	public int getEdadGato() {
		return edadGato;
	}

	public void setEdadGato(String edadGato) {
		int edadInt = Integer.parseInt(edadGato.trim());
		setEdadGato(edadInt);
	}

	public void setEdadGato(int edadGato) {
		if (edadGato >= 0) {
			this.edadGato = edadGato;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Peso Gato
	public float getPesoGato() {
		return pesoGato;
	}

	public void setPesoGato(String pesoGato) {
		float pesoFloat = Float.parseFloat(pesoGato.trim());
		setPesoGato(pesoFloat);
	}

	public void setPesoGato(float pesoGato) {
		if (pesoGato >= 0) {
			this.pesoGato = pesoGato;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Precio Gato
	public float getPrecioAdopcion() {
		return precioAdopcion;
	}

	public void setPrecioAdopcion(String precioGato) {
		float precioFloat = Float.parseFloat(precioGato.trim());
		setPrecioAdopcion(precioFloat);
	}

	public void setPrecioAdopcion(float precioAdopcion) {
		if (precioAdopcion > 0 && precioAdopcion <= 10000) {
			this.precioAdopcion = precioAdopcion;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Nombre Gato
	public String getNombreGato() {
		return nombreGato;
	}

	public void setNombreGato(String nombreGato) {
		String nombreLimpio = nombreGato.trim();
		if (!nombreLimpio.isEmpty()) {
			this.nombreGato = nombreLimpio;
		} else {
			throw new IllegalArgumentException("El nombre no puede estar vacío");
		}
	}

	// Identificador Gato
	public String getIdentificadorGato() {
		return identificadorGato;
	}

	public void setIdentificadorGato(String idGato) {
		String idLimpio = idGato.trim();
		String regex = "[A-Z]{3}[0-9]{4}[HM]";
		boolean comprobarFormato = idLimpio.matches(regex);

		if (comprobarFormato) {
			this.identificadorGato = idLimpio;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Nacimiento Gato
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	// Sexo Gato
	public String getSexoGato() {
		return sexoGato;
	}

	public void setSexoGato(String sexoGato) {
		if (sexoGato.equals("Macho") || sexoGato.equals("Hembra")) {
			this.sexoGato = sexoGato;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Esterilización Gato
	public boolean getGatoEsterilizado() {
		return gatoEsterilizado;
	}

	public void setGatoEsterilizado(boolean gatoEsterilizado) {
		this.gatoEsterilizado = gatoEsterilizado;
	}

	// Personalidad Gato
	public String getPersonalidad() {
		return personalidad;
	}

	public void setPersonalidad(String personalidad) {
		this.personalidad = personalidad;
	}

	// Hábitos Entrenamiento
	public ArrayList<String> getHabitosEntrenamiento() {
		return habitosEntrenamiento;
	}

	public void setHabitosEntrenamiento(ArrayList<String> habitosEntrenamiento) {
		this.habitosEntrenamiento = habitosEntrenamiento;
	}

	// Color Gato
	public ArrayList<String> getColorGato() {
		return colorGato;
	}

	public void setColorGato(ArrayList<String> colorGato) {
		this.colorGato = colorGato;
	}

	// Ruta imagen
	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	@Override
	public String toString() {
		return identificadorGato + " " + nombreGato + " " + edadGato + " " + pesoGato;
	}
}
