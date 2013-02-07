package Data;



public class Data {
public static String name = "&5"; //craighep
public static String pass = "CP"; //f1f2f3
public static boolean b;

public boolean checkName(String namen, String passn) {
	CEncrypt en = new CEncrypt();
	namen = en.en(namen);
	passn= en.en(passn);
		
    if (name.equals(namen) & (pass.equals(passn))){b = true;
}
    else {b = false;}
    
    
    return b;
}

}
