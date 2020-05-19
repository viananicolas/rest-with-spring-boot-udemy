package br.com.spring.restwithspringbootudemy.controller;

import br.com.spring.restwithspringbootudemy.math.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
public class CalculationController {
    @Autowired
    private Calculator calculator;

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo") Double numberTwo){
        return calculator.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo") Double numberTwo){
        return calculator.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo") Double numberTwo){
        return calculator.multiplication(numberOne, numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo") Double numberTwo){
        return calculator.division(numberOne, numberTwo);
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo") Double numberTwo){
        return calculator.mean(numberOne, numberTwo);
    }

    @RequestMapping(value = "/squareroot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable("number") Double number){
        return calculator.squareRoot(number);
    }
}
