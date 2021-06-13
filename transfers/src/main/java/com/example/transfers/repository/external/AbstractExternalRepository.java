package com.example.transfers.repository.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public abstract class AbstractExternalRepository<T> {
	private RestTemplate restTemplate;
	private final Class<T> entity;

	@Autowired
	protected void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private HttpEntity<T> getHttpEntity() {
		return getHttpEntity(null);
	}

	private HttpEntity<T> getHttpEntity(T entity) {
		return new HttpEntity<>(entity, null);
	}

	private ResponseEntity<T> getForEntity(URI uri, Class<T> clazz) {
		try {
			return restTemplate.exchange(uri, HttpMethod.GET, getHttpEntity(), clazz);
		} catch (HttpClientErrorException.NotFound ignored) {
			return ResponseEntity.of(Optional.empty());
		}
	}

	private ResponseEntity<T> putForEntity(URI uri, T body) {
		return restTemplate.exchange(uri, HttpMethod.PUT, getHttpEntity(body), this.entity);
	}

	private ResponseEntity<T> getForEntity(URI uri) {
		return getForEntity(uri, this.entity);
	}

	protected Optional<T> get(URI uri) {
		return Optional.ofNullable(getForEntity(uri).getBody());
	}

	protected Optional<T> update(URI uri, T body) {
		return Optional.ofNullable(putForEntity(uri, body).getBody());
	}

	protected UriComponentsBuilder getUriComponentsBuilder(String baseServiceUrl) {
		return UriComponentsBuilder.fromUriString(baseServiceUrl);
	}

	protected URI getURI(String baseServiceUrl, String... pathSegments) {
		return getUriComponentsBuilder(baseServiceUrl)
				.pathSegment(pathSegments)
				.build()
				.toUri();
	}
}
