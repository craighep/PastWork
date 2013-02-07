import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class crack {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String password;
		boolean fag = true;
		boolean poo = false;
		boolean poop=false;
		boolean again=true;
		boolean again2=true;
		boolean again3=true;
		boolean poopy=false;
		boolean again4=true;
		boolean poopy2=false;
		boolean again5=true;
		boolean poopy3=false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(fag == true){
		
		System.out.println("Please Type A Password: (Max 4 long)");
		password =br.readLine();
		System.out.println("Please Wait...");
long start;
		start = System.currentTimeMillis();
		 for(int e =-35; e<36;e++){
				
				String newchar5 = ascii(e);
				if (poopy3==true){newchar5= newchar5.toUpperCase();}
				if (again5 == true){
				if (e == 35){e=11; poopy3 = true;again5 = false;}}
				poopy2=false;
				again4=true;
				
		
      for(int d =-35; d<36;d++){
			
			String newchar4 = ascii(d);
			if (poopy2==true){newchar4= newchar4.toUpperCase();}
			if (again4 == true){
			if (d == 35){d=11; poopy2 = true;again4 = false;}}
			poopy=false;
			again3=true;
			
		for(int c =-35; c<36;c++){
			
			String newchar3 = ascii(c);
			if (poopy==true){newchar3= newchar3.toUpperCase();}
			if (again3 == true){
			if (c == 35){c=11; poopy = true;again3 = false;}}
			poo=false;
			again2=true;
			
			for(int b =-35; b<36;b++){
			String newchar2 = ascii(b);
			if (poo==true){newchar2= newchar2.toUpperCase();}
			if (again2 == true){
			if (b == 35){b=11; poo = true;again2 = false;}}
			poop=false;
			again=true;
			
			for(int i= -35; i<36; i++){
				String newchar = ascii(i);
				if (poop==true){newchar= newchar.toUpperCase();}
				if(again == true){
				if (i == 35){i=11; poop = true;again = false;}}
				
				String guess = (newchar5+""+newchar4 +""+newchar3+ ""+newchar2+ ""+newchar);
				if(password.equals(guess)){System.out.println("Password Was: "+guess); 
				long end = System.currentTimeMillis();
				long finish = end-start;
				float myfloat = (float)(finish);
				System.out.println("");
				System.out.println("Execution speed "+ ((myfloat)/1000)+" seconds\n");
				}
			}}
	}}}}}
	
	
			public static String ascii(int i){
				String letter = "";
				int t = i;
				if (t==-35){letter = "";}
				if (t==-34){letter = "¬";}
				if (t==-33){letter = "!";}
				if (t==-32){letter = "|";}
				if (t==-31){letter = "£";}
				if (t==-30){letter = "$";}
				if (t==-29){letter = "%";}
				if (t==-28){letter = "^";}
				if (t==-27){letter = "&";}
				if (t==-26){letter = "*";}
				if (t==-25){letter = "(";}
				if (t==-24){letter = ")";}
				if (t==-23){letter = "-";}
				if (t==-22){letter = "_";}
				if (t==-21){letter = "=";}
				if (t==-20){letter = "+";}
				if (t==-19){letter = ",";}
				if (t==-18){letter = "<";}
				if (t==-17){letter = ".";}
				if (t==-16){letter = ">";}
				if (t==-15){letter = ";";}
				if (t==-14){letter = ":";}
				if (t==-13){letter = "/";}
				if (t==-12){letter = "?";}
				if (t==-11){letter = "@";}
				if (t==-10){letter = "'";}
				if (t==-9){letter = "[";}
				if (t==-8){letter = "{";}
				if (t==-7){letter = "]";}
				if (t==-6){letter = "}";}
				if (t==-5){letter = "#";}
				if (t==-4){letter = "~";}
				if (t==-3){letter = "";}// need to know how to do a '\'
				if (t==-2){letter = " ";}
				if (t==-1){letter = "`";}
				if (t==0){letter = "0";}
				if (t==1){letter = "1";}
				if (t==2){letter = "2";}
				if (t==3){letter = "3";}
				if (t==4){letter = "4";}
				if (t==5){letter = "5";}
				if (t==6){letter = "6";}
				if (t==7){letter = "7";}
				if (t==8){letter = "8";}
				if (t==9){letter = "9";}
				if (t==10){letter = "a";}
				if (t==11){letter = "b";}
				if (t==12){letter = "c";}
				if (t==13){letter = "d";}
				if (t==14){letter = "e";}
				if (t==15){letter = "f";}
				if (t==16){letter = "g";}
				if (t==17){letter = "h";}
				if (t==18){letter = "i";}
				if (t==19){letter = "j";}
				if (t==20){letter = "k";}
				if (t==21){letter = "l";}
				if (t==22){letter = "m";}
				if (t==23){letter = "n";}
				if (t==24){letter = "o";}
				if (t==25){letter = "p";}
				if (t==26){letter = "q";}
				if (t==27){letter = "r";}
				if (t==28){letter = "s";}
				if (t==29){letter = "t";}
				if (t==30){letter = "v";}
				if (t==31){letter = "u";}
				if (t==32){letter = "w";}
				if (t==33){letter = "x";}
				if (t==34){letter = "y";}
				if (t==35){letter = "z";}
				
				
				
				
				return letter;
				
			}
			
		

	
	}


