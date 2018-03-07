import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Finance {

	HashSet<String> hrefs;

	private int contador;

	private List<App> listaApps;

	public Finance() throws InterruptedException {



		//Lista en donde se guardarï¿½ le informaciï¿½n de los apps(vï¿½ase clase App)
		listaApps = new ArrayList<App>();

		//Contador de las aplicaciones
		contador =0;

		// son los documentos que guardaran la informaiciï¿½n de los HTML 
		Document[] arregloDocumento= new Document[3];


		try {
			// la informaci?n "start=0&num=60" de la url parmite agrupar las apps , en este caso start implica que se emplieza desde la app 0 y num indica la cantidad de elementos 
			// que se pueden obtener (usualemtne 60 es lo m?ximo que permite)


			int start= 0;

			int num = 120;

			boolean entro = true;

			for (int i = 0; i<arregloDocumento.length; i++) 
			{
				if (entro)
				{
					arregloDocumento[i] =  (Document) Jsoup.connect ("https://play.google.com/store/apps/category/FINANCE?start=0&num=100").timeout(0).maxBodySize(0).get();
					entro = false;
				}
				arregloDocumento[i] =  (Document) Jsoup.connect ("https://play.google.com/store/apps/category/FINANCE/collection/topselling_free?start="+start+"&num="+num).timeout(0).maxBodySize(0).get();
				start +=60;
			}



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i<arregloDocumento.length; i++) {

			decodificarYGuardar(arregloDocumento[i]);
		}

	}

	public void decodificarYGuardar(Document doc) {

		hrefs = new HashSet<String>();

		Document detailDoc = null;

		Elements anchors = doc.getElementsByClass("card-click-target");
		for(Element element: anchors) {

			hrefs.add("https://play.google.com/" +element.attr("href").toString());
		}

		String nombre, numeroRatings, ratings5, ratings4,
		ratings3, ratings2, ratings1, ratingPromedio, cambiosRecientes, descripcion, apk = null;

		double ratingPromedio2 = 0;

		int numeroRatings2, ratings5Estrellas, ratings4Estrellas, ratings3Estrellas, ratings2Estrellas, ratings1Estrellas = 0; 

		for(String url: hrefs) {
			try {


				detailDoc = Jsoup.connect(url).timeout(0).get();
				nombre= detailDoc.select("[class=\"id-app-title\"]").text();

				ratings5 = detailDoc.select("[class=\"rating-bar-container five\"]").select("[class=\"bar-number\"]").text().replace(",", "");				
				if(ratings5.equals(""))ratings5 = "-1";
				ratings5Estrellas = Integer.parseInt(ratings5);				

				ratings4 = detailDoc.select("[class=\"rating-bar-container four\"]").select("[class=\"bar-number\"]").text().replace(",", "");				
				if(ratings4.equals(""))ratings4 = "-1";
				ratings4Estrellas = Integer.parseInt(ratings5);				

				ratings3 = detailDoc.select("[class=\"rating-bar-container three\"]").select("[class=\"bar-number\"]").text().replace(",", "");				
				if(ratings3.equals(""))ratings3 = "-1";
				ratings3Estrellas = Integer.parseInt(ratings5);				

				ratings2 = detailDoc.select("[class=\"rating-bar-container two\"]").select("[class=\"bar-number\"]").text().replace(",", "");				
				if(ratings2.equals(""))ratings2 = "-1";
				ratings2Estrellas = Integer.parseInt(ratings5);				

				ratings1 = detailDoc.select("[class=\"rating-bar-container one\"]").select("[class=\"bar-number\"]").text().replace(",", "");				
				if(ratings1.equals(""))ratings1 = "-1";
				ratings1Estrellas = Integer.parseInt(ratings5);				

				numeroRatings = detailDoc.select("[class=\"reviews-num\"]").text().replace(",", "");;
				if(numeroRatings.equals(""))numeroRatings = "-1";
				numeroRatings2 = Integer.parseInt(numeroRatings);

				ratingPromedio= detailDoc.select("[class=\"score\"]").text().replace(",", ".");
				if(ratingPromedio.equals(""))ratingPromedio = "-1";
				ratingPromedio2 = Double.parseDouble(ratingPromedio);

				descripcion= detailDoc.select("[class=\"description\"]").text();
				cambiosRecientes = detailDoc.select("[class=\"recent-change\"]").text();
				apk = detailDoc.select("[data-docid]").attr("data-docid");

				App app = new App(contador, nombre, numeroRatings2, ratingPromedio2, descripcion, cambiosRecientes, apk, 
						ratings5Estrellas, ratings4Estrellas, ratings3Estrellas, ratings2Estrellas, ratings1Estrellas);
				contador++;

				listaApps.add(app);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}

	public void darInfoPlayStore() {

		for(int i = 0; i<listaApps.size();i++) {

			System.out.println(listaApps.get(i).getId()+ ". " +listaApps.get(i).getNombre());
			System.out.println("--------------------------------------------------------------------------");

		}
	}
	public void darInfoApp(int i) {
		App p = listaApps.get(i);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(p.getId()+ ". " +p.getNombre());
		System.out.println("Número de ratings : "+p.getNumeroRatings() );
		System.out.println("Estrellas - Cinco : "+p.getRatings5Estrellas() + " Cuatro : " + 
				p.getRatings4Estrellas() + " Tres : " + p.getRatings3Estrellas() + " Dos : " + p.getRatings2Estrellas() + " Una : " + p.getRatings1Estrellas());
		System.out.println("Rating promedio : " +p.getRatingPromedio());
		System.out.println("Descripcion : "+ p.getDescripcion());
		System.out.println("Cambios recientes : "+ p.getCambiosRecientes());
		System.out.println("APK: "+ p.getApk());
		System.out.println("--------------------------------------------------------------------------");
	}





	public static void main (String[] args) throws InterruptedException {

		Finance finance = new Finance();

		Scanner reader = new Scanner(System.in);

		int n, x ;

		while(true) {		
			System.out.println("Que información desea de la PlayStore , Categoria Finanzas");
			System.out.println("1. Lista de las aplicaciones con toda su información (Escriba 1)");
			System.out.println("2. Dar info de una aplicación (Escriba 2)");
			System.out.println("3. Finalizar (Escriba 3)");


			try {

				n = reader.nextInt(); // Scans the next token of the input as an int.
				if(n == 1)finance.darInfoPlayStore();
				else if(n == 2) {
					System.out.println("Ingrese el ID en la lista de la aplicación (Escriba el id de la aplicación)");
					x = reader.nextInt();
					finance.darInfoApp(x);
					System.out.println("Escriba cualquier cosa para continuar");
					reader.next();
				}else if(n == 3) {
					break;
				}else {
				}

			}catch(InputMismatchException e) {
				System.out.println("Opción Incorrecta :  Escriba cualquier cosa para continuar");
				reader.next();
			}
		}
		reader.close();
	}

}