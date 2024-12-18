class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   
    // Array1: Karl benz name characters
    char[] sub = new char[8];
    sub[0] = 'k';
    sub[1] = 'a';
    sub[2] = 'r';
    sub[3] = 'l';
    sub[4] = 'b';
	sub[5] = 'e';
	sub[6] = 'n';
	sub[7] = 'z';
    // Array2: Unicode characters(Dingbats)
    char[] sub2 = new char[8];
    sub2[0] = '\u2756';  // BLACK DIAMOND MINUS WHITE X
    sub2[1] = '\u274F';  // LOWER RIGHT DROP-SHADOWED WHITE SQUARE
    sub2[2] = '\u2750';  // UPPER RIGHT DROP-SHADOWED WHITE SQUARE
    sub2[3] = '\u2751';  // LOWER RIGHT SHADOWED WHITE SQUARE
    sub2[4] = '\u2752';  // UPPER RIGHT SHADOWED WHITE SQUARE
	sub2[5] = '\u16BC';  // RUNIC LETTER LONG-BRANCH-HAGALL H
    sub2[6] = '\u16D4';  // RUNIC LETTER DOTTED-P
	sub2[7] = '\u16E4';  // RUNIC LETTER CEALC
    
	// // Encoding the plaintext:
    String file = Input.readFile("Original.txt");
    // Encode level 1 (substitution)
    String encodedMsg1 = subEncryption(file,sub,sub2);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (cipher with no wrap)
    String encodedMsg2 = encode(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // // Encode level 3 (string manipulation - reverse)
    String encodedMsg3 = reversE(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

    
    // // Decoding the ciphertext: 
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (string manipulation - reverse)
    String decodedMsg1 = reversE(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (cipher with no wrap)
    String decodedMsg2 = decode(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (substitution)
    String decodedMsg3 = subEncryption(decodedMsg2, sub2, sub);
    Input.writeFile("Decode3.txt", decodedMsg3);
    
    
  }
  // Cut a String
	String reversE(String txt) {
    String build = "";
    int leng = txt.length();
    
    // Split the string into two halves
    String fHalf = txt.substring(0,leng/2);
    String sHalf = txt.substring(leng/2);
    
    // Combine the second half followed by the first half
    build = sHalf + fHalf;
    
    return build;
}

	  
	  
 
  // reverse a string
  // String reverse(String txt){
    // String build ="";
    // for(int x=0; x<= txt.length()-1; x++){
      // build = txt.charAt(x) + build;
    // }
    // return build;
  // }
  
  
  // Cipher +1 encoding with no wrapping
  String encode(String txt){
    String build = "";
    int ascii = 0;
     char ch = '\0';
    
    for(int x=0; x<=txt.length()-1; x++){
      ch = txt.charAt(x);
      ascii = (int)ch;
      ascii += 3;
      
      build += (char)ascii;
    }     
    return build;
  }

  // Cipher -1 encoding with no wrapping
  String decode(String txt){
    String build="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=txt.length()-1; x++){
      ch=txt.charAt(x);
      ascii = (int)ch;
      ascii -= 3;
        build += (char)ascii;
    }
    return build;
  }

  // Substitution encoding
  String subEncryption(String s, char[] sub, char[] sub2){
    String build = "";
    char ch ='\0';
    int index=0;
    
    for(int x=0; x<=s.length()-1; x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }

		  
	  
  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }

  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }

}