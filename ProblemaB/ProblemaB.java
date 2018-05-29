import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * 
 */

/**
 * @author JulianBermudez
 *
 */
public class ProblemaB {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		ProblemaB problemaB = new ProblemaB();	

		try ( 
				InputStreamReader is = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) {
			String line = br.readLine();

			while (line != null && line.length() > 0) {
				final String [] dataStr = line.split(" ");
				long k = Long.parseLong(dataStr[0]);
				String[] rStrArray = dataStr[1].split("");

				while (problemaB.esPermutable(rStrArray) && k > 0) {
					rStrArray = problemaB.permutar(rStrArray);
					k--;
				}

				if (!problemaB.esPermutable(rStrArray)) {
					System.out.println("*");
				}
				else {
					System.out.println(problemaB.aString(rStrArray));
				}

				line = br.readLine();
			}
		}
	}

	private String[] permutar(String[] nums) {
		int i = nums.length - 2;

		while (i >= 0 && Integer.parseInt(nums[i]) >= Integer.parseInt(nums[i + 1])) {
			i--;
		}

		if (i >= 0) {
			int j = nums.length - 1;

			while (j >= 0 && Integer.parseInt(nums[j]) <= Integer.parseInt(nums[i])) {
				j--;
			}

			intercambiar(nums, i, j);
			reverso(nums, i + 1);
		}

		return nums;
	}

	private void reverso(String[] nums, int inicio) {
		int i = inicio, j = nums.length - 1;

		while (i < j) {
			intercambiar(nums, i, j);
			i++;
			j--;
		}
	}

	private void intercambiar(String[] nums, int i, int j) {
		String temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private boolean esPermutable(String nums[]) {
		int i = nums.length - 2;

		while (i >= 0 && Integer.parseInt(nums[i]) >= Integer.parseInt(nums[i + 1])) {
			i--;
		}

		if (i < 0)
			return false;

		return true;
	}

	private String aString(String[] a) {
		String ans = "";

		for (int i = 0; i < a.length; i++) {
			ans += a[i];
		}

		return ans;
	}
}
