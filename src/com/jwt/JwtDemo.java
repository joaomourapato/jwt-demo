package com.jwt;

import java.io.IOException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtDemo {
	
	//Provided secret to sign JWT with the HS512 algorithm:
	private static final String SECRET = "a9ddbcaba8c0ac1a0a812dc0c2f08514b23f2db0a68343cb8199ebb38a6d91e4ebfb378e22ad39c2d01d0b4ec9c34aa91056862ddace3fbbd6852ee60c36acbf"; 


	/*
	 * Main method. Runs iteratively by prompting 3 options:
	 * (1) Generates a new JWT. 
	 * 		- User needs to type the "data" input to include in the payload. 
	 * 		- The resulting token is displayed at the end.
	 * (2) Verifies a JWT. 
	 * 		- User needs to type a JWT string.
	 * 		- signature is verified and the claims displayed. 
	 * (Other) Exit.
	 */
	public static void main(String[] args) {
		try {
			
			MacAlgorithm alg = Jwts.SIG.HS512;
			SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
			
			while(true) {
				System.out.println();
				System.out.print("Insert option... (1) Generate JWT / (2) Verify JWT / (Other) Exit >> ");
				String option = getInputData();
				if (option.trim().equals("1")) {
					System.out.println("Generating JWT ...");
					System.out.print("Insert data >> ");
					String data = getInputData();
					String jwtOutput = createJwtToken(key, data, alg);
					System.out.println("Result JWT: " +jwtOutput);
				}
				else if (option.trim().equals("2")) {
					System.out.println("Verifying JWT ...");
					System.out.print("Insert token to verify >> ");
					String jwtInput = getInputData();
					boolean verifyResult = verifyJwtToken(jwtInput, key,  alg);
					if (verifyResult) {
						System.out.println("VERIFIED OK!");
					}
					else {
						System.out.println("NOT VALID JWT!");
					}
				}
				else {
					System.out.println("Exit!");
					System.exit(0);
				}
			}
			
			
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	/*
	 * Creates the JWT according to the specified. 
	 */
	private static String createJwtToken(SecretKey key, String data, MacAlgorithm alg) {
		

		Date date = new Date();
		
		String jwt = Jwts.builder()
			.issuedAt(date)
			.id(generateNonce(data))
			.subject("{\"data\": "+data+", \"date\": "+ date.toString() +"}")
			.signWith(key, alg)
			.compact();
		
		return jwt;
	}
	
	/*
	 * Verifies the JWT, including its signature and the extracted claims.
	 */
	private static boolean verifyJwtToken(String jwt, SecretKey key, MacAlgorithm alg) throws JwtException{

		 try {
			JwtParserBuilder parserBuilder = Jwts.parser();
			
			//verify signature
			JwtParser parser = parserBuilder.verifyWith(key).build();
			
			//verify token
			Jws<Claims> claims = parser.parseSignedClaims(jwt);
			
			//extract subject
			String payload = claims.getPayload().getSubject();	
			Date issuedAt = claims.getPayload().getIssuedAt();
			String id = claims.getPayload().getId();
			System.out.println("Extracted id: "+id);
			System.out.println("Extracted issuedAt: "+issuedAt.toString());
			System.out.println("Extracted payload: "+payload);
			return true;
		 }
		 catch (JwtException jwtEx) {
			 return false;
		 }
		
	}

	/*
	 * Reads console inputs.
	 */
	private static String getInputData() throws IOException {
		BufferedReader br = new BufferedReader(
	            new InputStreamReader(System.in));
	 
	        String name = br.readLine();
	 
	        return name;
	}
	

	
	/*
	 * Generates a unique nonce based on a Base64 encode of the typed data concatenated with the current timestamp. 
	 */
	private static String generateNonce(String data) { //throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException
        String nonceString = data.concat(Long.toString(new Date().getTime()));
        byte[] nonceByte = nonceString.getBytes();
        return Base64.getEncoder().encodeToString(nonceByte);
    }
	
}
