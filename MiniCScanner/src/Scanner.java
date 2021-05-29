import java.io.*;

public class Scanner {

    private boolean isEof = false;
    private char ch = ' '; 
    private BufferedReader input;
    private String line = "";
    private int lineno = 0;
    private int col = 1;
    private int saveCol = 1;	// (add)
    private final String letters = "abcdefghijklmnopqrstuvwxyz"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";
    private final String octalDigits = "01234567";	// (add)
    private final String addhexaValue = "ABCDEFabcdef";	// (add)
    private final char eolnCh = '\n';
    private final char eofCh = '\004';
    private String fileName;	// (add) + token property
    private boolean isDouble = false;	// (add) + double check
    

    public Scanner (String fileName) { // source filename
    	this.fileName = fileName;
    	System.out.println("Begin scanning... programs/" + fileName + "\n");
        try {
            input = new BufferedReader (new FileReader(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
    }

    private char nextChar() { // Return next char
        if (ch == eofCh)
            error("Attempt to read past end of file");
        col++;
        if (col >= line.length()) {
            try {
                line = input.readLine( );
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } // try
            if (line == null) // at end of file
                line = "" + eofCh;
            else {
                // System.out.println(lineno + ":\t" + line);
                lineno++;
                line += eolnCh;
            } // if line
            col = 0;
        } // if col
        saveCol = col;
        return line.charAt(col);
    }
            

    public Token next( ) { // Return next token
        do {        		
        	isDouble = false;
            if (isLetter(ch) || ch == '_') { // ident or keyword
                String spelling = concat(letters + digits + '_');
                return additionalPropertyToken(Token.keyword(spelling));
            } else if (isDigit(ch)) { // int literal
            	
            	if(ch == '0') {	 // (add) add character '.' to concat for double  
            		ch = nextChar();
            		if(ch == 'x' || ch == 'X') {	// hexadecimal (add)
            			ch = nextChar();
            			String hexNumber = concat(digits+addhexaValue+'.');	// .은 double형+ short from e.g. 1234.	(add)
            	    	// hexadecimal -> Decimal
            	    	String DecimalNumber = hexToDecimal(hexNumber);
            			if(isDouble == true) {
            				return additionalPropertyToken(Token.mkDoubleLiteral(DecimalNumber));	// double
            			}
            			else return additionalPropertyToken(Token.mkIntLiteral(DecimalNumber));
            		}
            		else if(isDigit(ch)){	// octal (add)
            			String octNumber = concat(octalDigits+'.');	// .은 double형+ short from e.g. 1234.	(add)
            			// octal -> decimal (add)
            			String DecimalNumber = octToDecimal(octNumber);
            			if(isDouble == true) {
            				return additionalPropertyToken(Token.mkDoubleLiteral(DecimalNumber));	// double
            			}
            			else return additionalPropertyToken(Token.mkIntLiteral(DecimalNumber));
            		}            	
            		else if (ch == '.') {	// double form e.g. 0.1234
                		ch = nextChar();
                		String number = concat(digits);
                        if (ch == 'e' || ch == 'E') {	//  e or E to scientific notation 
                        	ch = nextChar();
                        	if(ch == '+') {
                        		ch = nextChar();
                        		String exponential = concat(digits);
                        		String result = scientificNotation(number, exponential, '+');
                        		return additionalPropertyToken(Token.mkDoubleLiteral(result));
                        	}
                        	else if(ch == '-') {
                        		ch = nextChar();
                        		String exponential = concat(digits);
                        		String result = scientificNotation(number, exponential, '-');
                        		return additionalPropertyToken(Token.mkDoubleLiteral(result));
                        	}
                        	else error("Illegal character " + ch); 
                        }
                        else 
                        	return additionalPropertyToken(Token.mkDoubleLiteral("0."+number));
            		}
            	}
            	else {
            		String number = concat(digits+'.');	// 10 진법 .은 double형 (add) + short from e.g. 1234.
        			if(isDouble == true) {
        				return additionalPropertyToken(Token.mkDoubleLiteral(number));	// double
        			}
        			else return additionalPropertyToken(Token.mkIntLiteral(number));
            	}
            } else if (ch == '.') {	// shot form double e.g. .1234
        		ch = nextChar();
        		String shortFormDouble = concat(digits);
        		return additionalPropertyToken(Token.mkDoubleLiteral("0."+shortFormDouble));
        	}
            else switch (ch) {	// keyword
            case ' ': case '\t': case '\r': case eolnCh:
                ch = nextChar();
                break;
            
            case '/':  // divide or divAssign or comment
                ch = nextChar();
                if (ch == '=')  { // divAssign
                	ch = nextChar();
                	return additionalPropertyToken(Token.divAssignTok);
                }
                
                // divide
                if (ch != '*' && ch != '/') return additionalPropertyToken(Token.divideTok);
                
                // (add)
                // multiply line comment
                System.out.print("Documented Comments ------> ");
                String contents = "";
                if (ch == '*') { 
    				do {
    					while (ch != '*') {
    						contents += ch;
    						ch = nextChar();
    					}
    					ch = nextChar();
    				} while (ch != '/');
    				ch = nextChar();
    				
    				// (add) 
    				// /** */ comments
    				if(ch == '*') {
        				do {
        					while (ch != '*') ch = nextChar();
        					contents += ch;
        					ch = nextChar();
        				} while (ch != '/');
        				ch = nextChar();
    				}

                    System.out.println(contents);
                }
                // single line comment
                else if (ch == '/')  {
                	// (add) /// comments
                	ch = nextChar();
	                if(ch == '/') {
		                do {
		                    ch = nextChar();
		                	contents += ch;
		                } while (ch != eolnCh);
		                ch = nextChar();
	                }
	                else {	// // comments
	                	while (ch != eolnCh) {
	                		contents += ch;
	                		ch = nextChar();
	                	}
	                	contents += ch;
		                ch = nextChar();
	                }
	                System.out.print(contents);
                }
                break;
            
            case '\'':  // char literal
                char ch1 = nextChar();
                nextChar(); // get '
                ch = nextChar();
                return additionalPropertyToken(Token.mkCharLiteral("" + ch1));
              
            case eofCh: return additionalPropertyToken(Token.eofTok);
            
            case '+': 
            	ch = nextChar();
	            if (ch == '=')  { // addAssign
	            	ch = nextChar();
	            	return additionalPropertyToken(Token.addAssignTok);
	            }
	            else if (ch == '+')  { // increment
	            	ch = nextChar();
	            	return additionalPropertyToken(Token.incrementTok);
	            }
                return additionalPropertyToken(Token.plusTok);

            case '-': 
            	ch = nextChar();
                if (ch == '=')  { // subAssign
                	ch = nextChar();
                	return additionalPropertyToken(Token.subAssignTok);
                }
	            else if (ch == '-')  { // decrement
	            	ch = nextChar();
	            	return additionalPropertyToken(Token.decrementTok);
	            }
                return additionalPropertyToken(Token.minusTok);

            case '*': 
            	ch = nextChar();
                if (ch == '=')  { // multAssign
                	ch = nextChar();
                	return additionalPropertyToken(Token.multAssignTok);
                }
                return additionalPropertyToken(Token.multiplyTok);

            case '%': 
            	ch = nextChar();
                if (ch == '=')  { // remAssign
                	ch = nextChar();
                	return additionalPropertyToken(Token.remAssignTok);
                }
                return additionalPropertyToken(Token.reminderTok);

            case '(': ch = nextChar();
            return additionalPropertyToken(Token.leftParenTok);

            case ')': ch = nextChar();
            return additionalPropertyToken(Token.rightParenTok);

            case '{': ch = nextChar();
            return additionalPropertyToken(Token.leftBraceTok);

            case '}': ch = nextChar();
            return additionalPropertyToken(Token.rightBraceTok);

            case ';': ch = nextChar();
            return additionalPropertyToken(Token.semicolonTok);

            case ',': ch = nextChar();
            return additionalPropertyToken(Token.commaTok);
                
            case '&': check('&'); return additionalPropertyToken(Token.andTok);
            case '|': check('|'); return additionalPropertyToken(Token.orTok);

            case '=':
                return chkOpt('=', additionalPropertyToken(Token.assignTok),
                		additionalPropertyToken(Token.eqeqTok));

            case '<':
                return chkOpt('=', additionalPropertyToken(Token.ltTok),
                		additionalPropertyToken(Token.lteqTok));
            case '>': 
                return chkOpt('=', additionalPropertyToken(Token.gtTok),
                		additionalPropertyToken(Token.gteqTok));
            case '!':
                return chkOpt('=', additionalPropertyToken(Token.notTok),
                		additionalPropertyToken(Token.noteqTok));
            // (add) '[', ']'
            case '[': ch = nextChar();
            return additionalPropertyToken(Token.leftBracketTok);
            
            case ']': ch = nextChar();
            return additionalPropertyToken(Token.rightBracketTok);
            
            // (add) ':' colon operator
            case ':': ch = nextChar();
            	return additionalPropertyToken(Token.colonTok);
            // (add) 'stringLiteral' ["content"]
            case '"': ch = nextChar();
            
            String stringl = "";
    				do {
    					stringl += ch;
    					ch = nextChar();
    				} while (ch != '"');
    				ch = nextChar();

                   return additionalPropertyToken(Token.mkStringLiteral(stringl));
            
            default:  error("Illegal character " + ch); 
            } // switch
        } while (true);
    } // next


    private boolean isLetter(char c) {
        return (c>='a' && c<='z' || c>='A' && c<='Z');
    }
  
    private boolean isDigit(char c) {
        return (c>='0' && c<='9');
    }

    private void check(char c) {
        ch = nextChar();
        if (ch != c) 
            error("Illegal character, expecting " + c);
        ch = nextChar();
    }

    private Token chkOpt(char c, Token one, Token two) {
        ch = nextChar();
        if (ch != c)
            return one;
        ch = nextChar();
        return two;
    }

    private String concat(String set) {	// (add) . <- find double format
        String r = "";
        do {
            r += ch;
            ch = nextChar();
            if(ch == '.') isDouble = true;
        } while (set.indexOf(ch) >= 0);
        return r;
    }

    public void error (String msg) {
        System.err.print(line);
        System.err.println("Error: column " + col + " " + msg);
        System.exit(1);
    }
    
    // (add) additional property
    public Token additionalPropertyToken(Token token) {
    	token.setfileName(fileName);
    	token.setCol(saveCol);
    	token.setLineNo(lineno);
    	return token;
    }
    // (add) hexadecimal -> decimal
    public String hexToDecimal(String number) {
    	int num = Integer.parseInt(number, 16);
    	return String.valueOf(num);
    }
    // (add) octal -> decimal
    public String octToDecimal(String number) {
    	int num = Integer.parseInt(number, 8);
    	return String.valueOf(num);
    }
    
    // (add)  e or E to scientific notation
    public String scientificNotation(String num, String exp, char op) {
    	double number = Double.parseDouble("0."+num);
    	int exponential = Integer.parseInt(exp);
    	
    	if(op=='+') {
    		for(int i = 0 ; i < exponential; i++) {
    			number *= 10;
    		}	
    	}
    	else if(op=='-') {
    		for(int i = 0 ; i < exponential; i++) {
    			number /= 10;
    		}
    	}
    	return String.valueOf(number);
    }

    static public void main ( String[] argv ) {
        Scanner lexer = new Scanner(argv[0]);
        Token tok = lexer.next( );
        while (tok != Token.eofTok) {
            System.out.println(tok.toString());
            tok = lexer.next( );
        } 
    } // main
}
