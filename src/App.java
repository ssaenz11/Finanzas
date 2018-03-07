
public class App {
	
	
	private String apk;
	
	private int id;
	
	private String nombre;
	
	private int numeroRatings;
	
	private double ratingPromedio;
	
	private String descripcion;
	
	private String CambiosRecientes;
	
	private int ratings5Estrellas;
	
	private int ratings4Estrellas;
	
	private int ratings3Estrellas;
	
	private int ratings2Estrellas;
	
	private int ratings1Estrellas;
	
	public App(int id2, String nombre2, int numeroRatings2, double ratingPromedio2
			, String descripcion2, String cambiosRecientes2, String apk2
			, int estrellas5, int estrellas4, int estrellas3, int estrellas2, int estrellas1) {
		
		id = id2;
		nombre = nombre2;
		numeroRatings = numeroRatings2;
		ratingPromedio = ratingPromedio2;
		descripcion = descripcion2;
		CambiosRecientes = cambiosRecientes2; 
		apk = apk2;
		ratings1Estrellas = estrellas1;
		ratings2Estrellas = estrellas2;
		ratings3Estrellas = estrellas3;
		ratings4Estrellas = estrellas4;
		ratings5Estrellas = estrellas5;
		
	}

	public int getRatings5Estrellas() {
		return ratings5Estrellas;
	}

	public void setRatings5Estrellas(int ratings5Estrellas) {
		this.ratings5Estrellas = ratings5Estrellas;
	}

	public int getRatings4Estrellas() {
		return ratings4Estrellas;
	}

	public void setRatings4Estrellas(int ratings4Estrellas) {
		this.ratings4Estrellas = ratings4Estrellas;
	}

	public int getRatings3Estrellas() {
		return ratings3Estrellas;
	}

	public void setRatings3Estrellas(int ratings3Estrellas) {
		this.ratings3Estrellas = ratings3Estrellas;
	}

	public int getRatings2Estrellas() {
		return ratings2Estrellas;
	}

	public void setRatings2Estrellas(int ratings2Estrellas) {
		this.ratings2Estrellas = ratings2Estrellas;
	}

	public int getRatings1Estrellas() {
		return ratings1Estrellas;
	}

	public void setRatings1Estrellas(int ratings1Estrellas) {
		this.ratings1Estrellas = ratings1Estrellas;
	}

	public void setNumeroRatings(int numeroRatings) {
		this.numeroRatings = numeroRatings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroRatings() {
		return numeroRatings;
	}

	public double getRatingPromedio() {
		return ratingPromedio;
	}

	public void setRatingPromedio(double ratingPromedio) {
		this.ratingPromedio = ratingPromedio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCambiosRecientes() {
		return CambiosRecientes;
	}

	public void setCambiosRecientes(String cambiosRecientes) {
		CambiosRecientes = cambiosRecientes;
	}

	public String getApk() {
		return apk;
	}

	public void setApk(String apk) {
		this.apk = apk;
	}
	
	

}
