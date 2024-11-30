package com.example.infrastructures.event.schedulers;

import com.example.infrastructures.event.comptes.PreleveFrais;
import org.springframework.scheduling.annotation.Scheduled;

public class Schedules {
    @Scheduled(fixedRate = 5)
    public void runPreleveFrais() {
        PreleveFrais preleveFrais = new PreleveFrais();
        preleveFrais.preleveFrais();
    }
}