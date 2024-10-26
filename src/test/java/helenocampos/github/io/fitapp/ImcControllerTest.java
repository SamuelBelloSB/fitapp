package helenocampos.github.io.fitapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import helenocampos.github.io.fitapp.controller.ImcController;
import helenocampos.github.io.fitapp.service.GenderService;

public class ImcControllerTest {
    private ImcController imcController;
    private GenderService genderServiceMock;

    @BeforeEach
    public void setUp() {
        genderServiceMock = Mockito.mock(GenderService.class);
        imcController = new ImcController(genderServiceMock);
    }

    @Test
    public void testImcValue() {
        double result = imcController.getIMC(70, 1.70);
        double expected = 24.22;
        assertEquals(expected, result, 0.002);
    }

    @Test
    public void testImcValueAltura1() {
        double result = imcController.getIMC(50, 1);
        double expected = 50;
        assertEquals(expected, result, 0.002);
    }

    // Implementados por Sam
    @Test
    public void testGetIMCClassification () {
        // Testes classificar com entrada do imc
        assertEquals("Abaixo do peso", imcController.getIMCClassification(17.0));
        assertEquals("Peso normal", imcController.getIMCClassification(22.0));
        assertEquals("Excesso de peso", imcController.getIMCClassification(27.0));
        assertEquals("Obesidade classe I", imcController.getIMCClassification(32.0));
        assertEquals("Obesidade classe II", imcController.getIMCClassification(37.0));
        assertEquals("Obesidade classe III", imcController.getIMCClassification(41.0));
    }

    @Test
    public void testGetIMCClassificationByGender(){
        // Testes para gênero feminino
        assertEquals("Abaixo do peso", imcController.getIMCClassificationByGender(17.0, "female"));
        assertEquals("Peso normal", imcController.getIMCClassificationByGender(23.0, "female"));
        assertEquals("Obesidade leve", imcController.getIMCClassificationByGender(27.0, "female"));
        assertEquals("Obesidade moderada", imcController.getIMCClassificationByGender(38.0, "female"));
        assertEquals("Obesidade mórbida", imcController.getIMCClassificationByGender(43.0, "female"));
        
        // Testes para gênero masculino
        assertEquals("Abaixo do peso", imcController.getIMCClassificationByGender(18.0, "male"));
        assertEquals("Peso normal", imcController.getIMCClassificationByGender(23.0, "male"));
        assertEquals("Obesidade leve", imcController.getIMCClassificationByGender(28.0, "male"));
        assertEquals("Obesidade moderada", imcController.getIMCClassificationByGender(38.0, "male"));
        assertEquals("Obesidade mórbida", imcController.getIMCClassificationByGender(41.0, "male"));
    }

    @Test
    public void testGetGeneroPt() {
        // Teste para o gênero masculino
        assertEquals("Masculino", imcController.getGeneroPt("male"));
        
        // Teste para o gênero feminino
        assertEquals("Feminino", imcController.getGeneroPt("female"));
        
        // Teste para um gênero não definido
        assertEquals("Indefinido", imcController.getGeneroPt(""));
    }
}
