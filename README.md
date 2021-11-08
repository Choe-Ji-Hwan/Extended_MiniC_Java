# Extended_MiniC_Scanner_Java
Location of original file
https://github.com/PLASS-Lab/FLT-2019  PLASS-Lab / FLT-2019/MiniC Scanner/Java/MiniCScanner 
MiniC extended to java
+ add keyword (char, double, for, do, goto, switch, case, break, default)
+ add operator ( :(colon) )
+ add  literal (character, string, double)
+ add documented comments ( "///", "/** ~ */" )
+ add token property value (file name, line number, column number)

upload to 2021-05-29

# input(good.mc)
```
/* this is /comments */
/// /this is comments
// this /is comments
/** this is /comments */

void main()
{
   int a = 034;
   int b = 0x10;
   double c = 1234.;
   double d = .1234;
   double e = 12.34;
   double f = 0.12e+1;
   double g = 0.34e-1;
   char h = 'a';
   goto;
   do {
   for(;b!=16;b++);
   } while(b!=16);
   switch(a) {
      case 10 : write("hello"); break;
      case 11 : write(b); break;
      default : 
   }
}
```
# output
```
"C:\Program Files\Java\jdk1.8.0_271\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.1.3\lib\idea_rt.jar=62443:C:\Program Files\JetBrains\IntelliJ IDEA 2021.1.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_271\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\mysql-connector-java-8.0.22.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\rt.jar;C:\Users\wlghk\IdeaProjects\untitled\out\production\untitled" Scanner good.mc
Begin scanning... programs/good.mc

Documented Comments ------>  this is /comments 
Documented Comments ------>  /this is comments
Documented Comments ------>  this /is comments
Documented Comments ------>  this is /comments 
Token -----> Void (1, void, good.mc, 6, 4)
Token -----> Identifier (37, main, good.mc, 6, 9)
Token -----> LeftParen (11, (, good.mc, 6, 10)
Token -----> RightParen (12, ), good.mc, 6, 11)
Token -----> LeftBrace (7, {, good.mc, 7, 1)
Token -----> Int (4, int, good.mc, 8, 6)
Token -----> Identifier (37, a, good.mc, 8, 8)
Token -----> Assign (15, =, good.mc, 8, 9)
Token -----> IntLiteral (38, 28, good.mc, 8, 14)
Token -----> Semicolon (13, ;, good.mc, 8, 15)
Token -----> Int (4, int, good.mc, 9, 6)
Token -----> Identifier (37, b, good.mc, 9, 8)
Token -----> Assign (15, =, good.mc, 9, 9)
Token -----> IntLiteral (38, 16, good.mc, 9, 15)
Token -----> Semicolon (13, ;, good.mc, 9, 16)
Token -----> Double (40, double, good.mc, 10, 9)
Token -----> Identifier (37, c, good.mc, 10, 11)
Token -----> Assign (15, =, good.mc, 10, 12)
Token -----> DoubleLiteral (51, 1234., good.mc, 10, 19)
Token -----> Semicolon (13, ;, good.mc, 10, 20)
Token -----> Double (40, double, good.mc, 11, 9)
Token -----> Identifier (37, d, good.mc, 11, 11)
Token -----> Assign (15, =, good.mc, 11, 12)
Token -----> DoubleLiteral (51, 0.1234, good.mc, 11, 19)
Token -----> Semicolon (13, ;, good.mc, 11, 20)
Token -----> Double (40, double, good.mc, 12, 9)
Token -----> Identifier (37, e, good.mc, 12, 11)
Token -----> Assign (15, =, good.mc, 12, 12)
Token -----> DoubleLiteral (51, 12.34, good.mc, 12, 19)
Token -----> Semicolon (13, ;, good.mc, 12, 20)
Token -----> Double (40, double, good.mc, 13, 9)
Token -----> Identifier (37, f, good.mc, 13, 11)
Token -----> Assign (15, =, good.mc, 13, 12)
Token -----> DoubleLiteral (51, 1.2, good.mc, 13, 21)
Token -----> Semicolon (13, ;, good.mc, 13, 22)
Token -----> Double (40, double, good.mc, 14, 9)
Token -----> Identifier (37, g, good.mc, 14, 11)
Token -----> Assign (15, =, good.mc, 14, 12)
Token -----> DoubleLiteral (51, 0.034, good.mc, 14, 21)
Token -----> Semicolon (13, ;, good.mc, 14, 22)
Token -----> Char (39, char, good.mc, 15, 7)
Token -----> Identifier (37, h, good.mc, 15, 9)
Token -----> Assign (15, =, good.mc, 15, 10)
Token -----> CharacterLiteral (49, a, good.mc, 15, 15)
Token -----> Semicolon (13, ;, good.mc, 15, 16)
Token -----> Goto (43, goto, good.mc, 16, 7)
Token -----> Semicolon (13, ;, good.mc, 16, 8)
Token -----> Do (42, do, good.mc, 17, 5)
Token -----> LeftBrace (7, {, good.mc, 17, 7)
Token -----> For (41, for, good.mc, 18, 6)
Token -----> LeftParen (11, (, good.mc, 18, 7)
Token -----> Semicolon (13, ;, good.mc, 18, 8)
Token -----> Identifier (37, b, good.mc, 18, 9)
Token -----> NotEqual (27, !=, good.mc, 18, 9)
Token -----> IntLiteral (38, 16, good.mc, 18, 13)
Token -----> Semicolon (13, ;, good.mc, 18, 14)
Token -----> Identifier (37, b, good.mc, 18, 15)
Token -----> Increment (32, ++, good.mc, 18, 17)
Token -----> RightParen (12, ), good.mc, 18, 18)
Token -----> Semicolon (13, ;, good.mc, 18, 19)
Token -----> RightBrace (8, }, good.mc, 19, 4)
Token -----> While (5, while, good.mc, 19, 10)
Token -----> LeftParen (11, (, good.mc, 19, 11)
Token -----> Identifier (37, b, good.mc, 19, 12)
Token -----> NotEqual (27, !=, good.mc, 19, 12)
Token -----> IntLiteral (38, 16, good.mc, 19, 16)
Token -----> RightParen (12, ), good.mc, 19, 17)
Token -----> Semicolon (13, ;, good.mc, 19, 18)
Token -----> Switch (44, switch, good.mc, 20, 9)
Token -----> LeftParen (11, (, good.mc, 20, 10)
Token -----> CharacterLiteral (49, a, good.mc, 20, 11)
Token -----> RightParen (12, ), good.mc, 20, 12)
Token -----> LeftBrace (7, {, good.mc, 20, 14)
Token -----> Case (45, case, good.mc, 21, 10)
Token -----> IntLiteral (38, 10, good.mc, 21, 13)
Token -----> Colon (48, :, good.mc, 21, 15)
Token -----> Identifier (37, write, good.mc, 21, 21)
Token -----> LeftParen (11, (, good.mc, 21, 22)
Token -----> StringLiteral (50, hello, good.mc, 21, 29)
Token -----> RightParen (12, ), good.mc, 21, 30)
Token -----> Semicolon (13, ;, good.mc, 21, 31)
Token -----> Break (46, break, good.mc, 21, 37)
Token -----> Semicolon (13, ;, good.mc, 21, 38)
Token -----> Case (45, case, good.mc, 22, 10)
Token -----> IntLiteral (38, 11, good.mc, 22, 13)
Token -----> Colon (48, :, good.mc, 22, 15)
Token -----> Identifier (37, write, good.mc, 22, 21)
Token -----> LeftParen (11, (, good.mc, 22, 22)
Token -----> Identifier (37, b, good.mc, 22, 23)
Token -----> RightParen (12, ), good.mc, 22, 24)
Token -----> Semicolon (13, ;, good.mc, 22, 25)
Token -----> Break (46, break, good.mc, 22, 31)
Token -----> Semicolon (13, ;, good.mc, 22, 32)
Token -----> Default (47, default, good.mc, 23, 13)
Token -----> Colon (48, :, good.mc, 23, 15)
Token -----> RightBrace (8, }, good.mc, 24, 4)
Token -----> RightBrace (8, }, good.mc, 25, 1)

Process finished with exit code 0
```
