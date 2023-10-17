# jwt-demo
This tool demonstrates the creation and verification of JSON Web Tokens. Main purpose is: coding challenge. 
The tool imports open source libraries "jjwt" recommended by a JWT reference site: https://jwt.io/libraries. The libraries are documented and available from: https://github.com/jwtk/jjwt

**PRE-REQUISITES**
* Java SE or JRE minimum. No specific version but any version newer than 1.8 shall be ok.   

**HOW TO COMPILE AND RUN**
1. Extract the release files to a folder of file system
2. Use the command line and change to the main folder (/jwt-demo)
3. Assuming JDK available, type the following command to compile:
   > javac -classpath "lib/*" "src/com/jwt/JwtDemo.java" -d "bin"
4. To run the just compiled sources, type:
   > java -classpath "bin:lib/*" com.jwt.JwtDemo
5. Alternatively to (4) and (5), in case JDK is unavailable, a runnable jar is provided in the main folder. Type the following:
   > java -jar JwtDemo.jar  

**HOW TO USE**

The tool runs from the command line console and repeatedly prompts for an option. Type either 1 or 2. 

(1) Generates a new JWT. The user is asked to provide a value for the "data" parameter, part of the JWT payload.

(2) Verifies a JWT. The user is asked to provide the JWT string. For a successful verification, suggest to copy/paste the JWT that is output of option (1)

(Other digit/text): Quits the tool. 

