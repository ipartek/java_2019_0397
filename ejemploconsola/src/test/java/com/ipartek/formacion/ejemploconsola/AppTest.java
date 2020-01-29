package com.ipartek.formacion.ejemploconsola;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void sumar()
    {
        assertEquals(8, Biblioteca.sumar(5,3));
    }
}
