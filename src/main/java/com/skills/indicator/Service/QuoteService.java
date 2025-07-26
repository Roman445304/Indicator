package com.skills.indicator.Service;

import com.skills.indicator.Dto.QuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class QuoteService {

    private final RestTemplate restTemplate;

    private static final String QUOTES_API_URL = "https://zenquotes.io/api/random";

    @Autowired
    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getQuote(Model model) {

        QuoteDTO[] quoteDTOS = restTemplate.getForObject(QUOTES_API_URL, QuoteDTO[].class);
        if (quoteDTOS != null && quoteDTOS.length > 0) {
            QuoteDTO quoteDTO = quoteDTOS[0];
            model.addAttribute("quoteText", quoteDTO.getQ());
            model.addAttribute("quoteAuthor", quoteDTO.getA());
        }
        else {
            model.addAttribute("quoteText", "Не удалось получить цитату.");
            model.addAttribute("quoteAuthor", "Сервис недоступен");
        }
    }
}
