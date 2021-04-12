package com.pocs.geradornometimes.repositorio;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class GeradorRepositorio {
	
	@Autowired
	private RestTemplate restTemplate;

	public String getNome(Long codigoTime) {
		
		try {
			ResponseEntity<ResultResponse> time = restTemplate.getForEntity(new URI("http://localhost:8081/nome/time/".concat(String.valueOf(codigoTime))), ResultResponse.class);
			
			ResponseEntity<ResultResponse> cidade = restTemplate.getForEntity(new URI("http://localhost:8082/nome/cidade/".concat(String.valueOf(codigoTime))), ResultResponse.class);
			
			return time.getBody().getNome().concat(" de ").concat(cidade.getBody().getNome());
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	

}
