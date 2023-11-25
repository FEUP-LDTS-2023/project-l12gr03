package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.Controller;
import project.gui.GUI;


import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {

    private Controller<String> testController;

    @BeforeEach
    void setUp() {
        testController = new Controller<String>("TestModel") {
            @Override
            public void step(Game game, GUI.ACTION action, long time){

            }
        };
    }

    @Test
    void testGetModel() {
        assertEquals("TestModel", testController.getModel());
    }

}
