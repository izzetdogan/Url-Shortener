package com.shortener.controller;

import com.shortener.dto.UrlShortDto;
import com.shortener.request.UrlShortRequest;
import com.shortener.service.UrlShortService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UrlShortController {
    private final UrlShortService urlShortService;

    public UrlShortController(UrlShortService urlShortService) {
        this.urlShortService = urlShortService;
    }

    @GetMapping
    public ResponseEntity<List<UrlShortDto>> getAllUrls(){
        return ResponseEntity.ok(urlShortService.getAllUrls());
    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<UrlShortDto> getUrlByShortUrl(@Valid @PathVariable String shortUrl){
        return ResponseEntity.ok(urlShortService.getUrlByShortUrl(shortUrl));
    }
    @GetMapping("/goto/{shortUrl}")
    public ResponseEntity<UrlShortDto> redirectUrl(@Valid @NotEmpty  @PathVariable String shortUrl) throws URISyntaxException {
        UrlShortDto urlShortDto = urlShortService.getUrlByShortUrl(shortUrl);
        URI uri = new URI(urlShortDto.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(httpHeaders,HttpStatus.SEE_OTHER);
    }

    @PostMapping
    public ResponseEntity<UrlShortDto> createShortUrl(@Valid @RequestBody UrlShortRequest request){

        return new ResponseEntity<UrlShortDto>(urlShortService.createShortUrl(request),HttpStatus.CREATED);
    }

}
