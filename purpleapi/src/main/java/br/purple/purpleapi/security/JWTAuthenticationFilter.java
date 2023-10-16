package br.purple.purpleapi.security;

import java.util.ArrayList;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.purple.purpleapi.domain.dtos.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response) {
		throws AuthenticationException {
			try {
				CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
				Authentication authentication = authenticationManager.authenticate(authenticationToken);
				return authentication;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
		
			String username = ((UserSS) auth.getPrincipal()).getUsername();
			String token = jwtUtil.generateToken(username);
			response.setHeader("Access-Control-Allow-Origin", "*");
	        	response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	        	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, enctype, Location");
	        	response.setHeader("Authorization", "Bearer " + token);
		}
		
		@Override
		protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException failed) throws IOException, ServletException {
			
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private CharSequence json() {
			long date = new Date().getTime();
			return "{"
					+ "\"timestamp\": " + date + ", " 
					+ "\"status\": 401, "
					+ "\"error\": \"Não autorizado\", "
					+ "\"message\": \"Email ou senha inválidos\", "
					+ "\"path\": \"/login\"}";
		}
}
}