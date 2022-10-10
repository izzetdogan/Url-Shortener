package com.shortener.service;

import com.shortener.Util.StringGenerator;
import com.shortener.dto.UrlShortDto;
import com.shortener.exception.ResourceAlreadyExist;
import com.shortener.exception.ResourceNotFoundException;
import com.shortener.model.UrlShort;
import com.shortener.repository.UrlShortRepository;
import com.shortener.request.UrlShortRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlShortService {
    private  final UrlShortRepository urlShortRepository;
    private final StringGenerator stringGenerator;

    public UrlShortService(UrlShortRepository urlShortRepository, StringGenerator stringGenerator) {
        this.urlShortRepository = urlShortRepository;
        this.stringGenerator = stringGenerator;
    }

    public List<UrlShortDto> getAllUrls(){
        return urlShortRepository.findAll()
                .stream()
                .map(UrlShortDto::convert)
                .toList();
    }

    public UrlShortDto getUrlByShortUrl(String shortUrl) {
        UrlShort urlShort = urlShortRepository.findAllByShortUrl(shortUrl)
                .orElseThrow(()-> new ResourceNotFoundException("Url not found"));
        return UrlShortDto.convert(urlShort);
    }

    public UrlShortDto createShortUrl(UrlShortRequest request) {
        String shortUrl=  request.getShortUrl();
        if(urlShortRepository.findAllByShortUrl(request.getShortUrl()).isPresent()){
            throw  new ResourceAlreadyExist("Duplicate");
        }
        if(shortUrl == null || shortUrl.isEmpty()){
            shortUrl=generateShortUrl();
        }

        UrlShort urlShort = new UrlShort(request.getUrl(), shortUrl);
        urlShortRepository.save(urlShort);
        return UrlShortDto.convert(urlShort);

    }

    private String generateShortUrl(){
        String shortUrl = stringGenerator.generate();

        while(urlShortRepository.findAllByShortUrl(shortUrl).isPresent()){
            shortUrl = stringGenerator.generate();
        }

        return shortUrl;
    }
}
