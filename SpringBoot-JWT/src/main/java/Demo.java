import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Demo {

	public static void main(String[] args) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("ip", "123");
		claims.put("authorities",Arrays.asList("sss","ddd"));
		String twt = Jwts.builder()
				.setClaims(claims)
				.setId(new Random().toString())
				.setSubject("tttt")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 12000))
				.signWith(SignatureAlgorithm.HS512, "authorities")
				.compact();
		System.out.println(twt);
		System.out.println(Jwts.parser().setSigningKey("authorities").parseClaimsJws(twt).getBody().getExpiration());
		twt =  Jwts.builder().setClaims(Jwts.parser().setSigningKey("authorities").parseClaimsJws(twt).getBody())
				.signWith(SignatureAlgorithm.HS512, "authorities")
				.setExpiration(new Date(System.currentTimeMillis() + 50000))
				.compact();
		System.out.println(Jwts.parser().setSigningKey("authorities").parseClaimsJws(twt).getBody().getExpiration());
		
		System.out.println(twt);
	}

}
