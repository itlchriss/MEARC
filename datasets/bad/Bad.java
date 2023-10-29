public class Bad {
	//@ ensures (*If the username, the password, and other relevant information are provided, the result will be true.*);
	//@ semantics "other relevant information", [NN], 1, [*], token
	public static boolean login(String username, String password, String token) {
		return false;
	}
}
