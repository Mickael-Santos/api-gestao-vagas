package br.com.mickaelsantos.gestaovagasapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrimeiroTeste {

    @Test
    public void deve_ser_possivel_calcular_dois_numeros()
    {
        var result = calculate(2, 3);
        assertEquals(result, 5);

    }

    @Test
    public void validar_valores_incorretos()
    {
        var result = calculate(2, 3);
        assertNotEquals(result, 5);
    }

    public double calculate(double num1, double num2)
    {
        return num1 + num2;
    }
    
}
