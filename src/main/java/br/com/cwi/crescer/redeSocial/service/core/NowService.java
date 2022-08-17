package br.com.cwi.crescer.redeSocial.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NowService {

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
