package com.grupo1.app.helpers;

import java.time.LocalDate;

public class DateHelper {

    public static boolean dataAMaiorQueDataB(LocalDate a, LocalDate b){
        return a.isAfter(b);
    }
}
