package com.anke.EarthQuake.controller;

import com.anke.EarthQuake.exception.NoEarthquakesFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    /**
     * NoEarthquakesFoundException fırlatıldığında çalışacak metot
     *
     * @param ex fırlatılan exception
     * @return hata sayfası
     */
    @ExceptionHandler(NoEarthquakesFoundException.class)
    public ModelAndView handleNoEarthquakesFoundException(NoEarthquakesFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("noEarthquakes");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("days", ex.getDays());
        return modelAndView;
    }
}
