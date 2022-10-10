# Url-Shortener
Spring-Boot(Java + Kotlin) based REST-API that takes a URL and returns a shortened URL

Kotlin language is used on DTO and Model classes to not using Lombok or boilerplate codes

![example](https://user-images.githubusercontent.com/101109536/194917405-0e41424f-ea5b-49c3-ada2-0d481bcae845.gif)



## Dependendencies
<ul>
<li>Spring Web </li>
<li>Spring-Data-JPA </li>
<li>H2-Database </li>
<li>Spring-Validation </li>
<li>Kotlin</li>
</ul>

## API-Endpoints
You can acces the API on port <a>http://localhost:8080/api/v1 </a>

### POST /
```json
{
    "url": "https://github.com/izzetdogan",
    "shortUrl": "dogan-izzet" // it can be null
}
```

### Get /{shortUrl}
```json
{
    "id": 1,
    "url": "https://github.com/izzetdogan",
    "shortUrl": "dogan-izzet" // it returns random String if it is null "QNPJTRTWKU"
}
```
### Get /goto/{shortUrl}
This endpoint redirect the user to the valid http/https web site by using shortUrl
