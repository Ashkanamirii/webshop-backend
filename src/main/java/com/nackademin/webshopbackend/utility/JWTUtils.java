//package com.nackademin.webshopbackend.utility;
//
//import java.security.Key;
//import java.time.Duration;
//
//
//public class JWTUtils {
//
//	private final Key key;
//	private final Duration validity;
//
//	public JWTUtils(final Key key, final Duration validity) {
//		this.key = key;
//		this.validity = validity;
//	}

//	public String generateToken(final Users user) {
//
//		final String authorities = user.getAuthorities().stream()
//				.map(GrantedAuthority :: getAuthority)
//				.collect(Collectors.joining(","));
//
//		return Jwts.builder()
//				.setSubject(user.getUsername())
//				.claim("authorities", authorities)
//				.signWith(key)
//				.setIssuedAt(Date.from(Instant.now()))
//				.setExpiration(Date.from(Instant.now().plus(validity)))
//				.compact();
//	}

//	public Users validate(String token) {
//		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
///*
//String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
//					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//					stream(roles).forEach(role -> {
//						authorities.add(new SimpleGrantedAuthority(role));
//					});
// */
//
//		String authorities = (String) claims.get("authorities");
//		// kolla efter en bättre lösning TODO:
////		List<Role> roles = Arrays.stream(authorities.split(",")).map(r -> r.substring(5)).map(Role ::getName()).collect(Collectors.toList());
//		List<String> roles = Arrays.stream(authorities.split(","))
//				.map(r -> r.substring(5)).collect(Collectors.toList());
//		Users user = new Users();
//		user.setEmail(claims.getSubject());
//		for (String role : roles) {
//			user.getRoles().forEach(r -> r.setName(role));
//		}
//		return user;
//	}
//}

/*
public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secretKey;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }


    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }


    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
 */
//}