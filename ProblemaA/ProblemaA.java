import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author JulianBermudez
 *
 */
public class ProblemaA {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		ProblemaA problemaA = new ProblemaA();

		try ( 
				InputStreamReader is = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) {
			String line = br.readLine();

			while (line != null && line.length() > 0) {
				final String[] dataStr = line.split(" ");

				final int n = Integer.parseInt(dataStr[0]);
				final int c = Integer.parseInt(dataStr[1]);
				final String[] arreglo = problemaA.construirArreglo(dataStr, n);

				int cantCerosEnArreglo = problemaA.contarCeros(arreglo);

				if (cantCerosEnArreglo <= c) {
					System.out.println(n);
				}
				else if(cantCerosEnArreglo == n) {
					System.out.println(c);
				}
				else {
					final int[] indicesCeros = problemaA.obtenerIndicesCeros(arreglo);

					System.out.println(problemaA.calcularMayorSubarreglo(arreglo, indicesCeros, n, c));
				}

				line = br.readLine();
			}
		}
	}
	
	private int calcularMayorSubarreglo(String[] arreglo, int[] indicesCeros, int n, int c) {
		int maxLongitudSubarreglo = 0;
		
		for (int indiceCeroInicio = 0, indiceCesimoCero = c - 1;
				(indiceCeroInicio < indicesCeros.length) && (indiceCesimoCero < indicesCeros.length);
				indiceCeroInicio++, indiceCesimoCero++) {

			int ceroInicio = indicesCeros[indiceCeroInicio];
			int cEsimoCero = indicesCeros[indiceCesimoCero];

			int indiceIzq = ceroInicio;
			int indiceDer = cEsimoCero;

			while (indiceIzq > 0 && !arreglo[indiceIzq - 1].equals("0")) {
				indiceIzq--;
			}

			while (indiceDer < (n - 1) && !arreglo[indiceDer + 1].equals("0")) {
				indiceDer++;
			}

			if (((indiceDer - indiceIzq) + 1) > maxLongitudSubarreglo) {
				maxLongitudSubarreglo = (indiceDer - indiceIzq) + 1;
			}
		}
		
		return maxLongitudSubarreglo;
	}

	private String[] construirArreglo(String[] input, int longitud) {
		String[] arregloDesdeDos = new String[longitud];

		for (int i = 2; i < input.length; i++) {
			arregloDesdeDos[i - 2] = input[i];
		}

		return arregloDesdeDos;
	}

	private int[] obtenerIndicesCeros(String[] arreglo) {
		int cantCeros = contarCeros(arreglo);
		int[] indicesCeros = new int[cantCeros];

		for (int i = 0, j = 0; i < arreglo.length && j < indicesCeros.length; i++) {
			if (arreglo[i].equals("0")) {
				indicesCeros[j] = i;
				j++;
			}
		}

		return indicesCeros;
	}

	private int contarCeros(String[] arreglo) {
		int contadorCeros = 0;

		for (int i = 0; i < arreglo.length; i++) {
			if (arreglo[i].equals("0")) {
				contadorCeros++;
			}
		}

		return contadorCeros;
	}

}
