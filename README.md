# jwt-demo
This tool demonstrates the creation and validation of JSON Web Tokens. Main purpose is: coding challenge. 
The tool imports open source libraries "jjwt". These are documented and available from: https://github.com/jwtk/jjwt .
The signature algorithm is HS512 using a fixed secret.

### PRE-REQUISITES
* Java SE or JRE minimum. No specific version but any version newer than "8" shall be ok.   

### HOW TO COMPILE AND RUN**
1. Extract the release files to a folder of the file system
2. Use the command line and change to the main folder (/jwt-demo-0.1)
3. Assuming JDK available, type the following command to compile:
   > javac -classpath "lib/*" "src/com/jwt/JwtDemo.java" -d "bin"
4. To run the just compiled sources, type:
   > java -classpath "bin:lib/*" com.jwt.JwtDemo
5. Alternatively to (3) and (4), in case JDK is unavailable, a runnable jar is provided in the main folder. Type the following:
   > java -jar JwtDemo.jar  

>[!NOTE]
>The above procedure was tested in a macos environment. Slight differences were noted when testing the same in Windows:
- `On (3), the "bin" folder had to be manually created prior to the javac command.`
- `On (4), replace "bin:lib/*" by "bin;lib/*"`
>[!NOTE]
>On (5), an execution error may thrown if the java runtime has version lower than "21", since this was the version used to compile and generate the runnable jar. 

### HOW TO USE

The tool runs from the command line console and repeatedly prompts for an option. Type either 1 or 2. 

(1) Generates a new JWT. The user is asked to provide a value for the "data" parameter, part of the JWT payload.

(2) Validates a JWT. The user is asked to provide the JWT string. For a successful validation, suggest to copy/paste the JWT that is output of option (1)

(Other digit/text): Quits the tool. 



### REMARK
* Only one bonus point implemented: extend the utility with a possibility to validate the JWT
