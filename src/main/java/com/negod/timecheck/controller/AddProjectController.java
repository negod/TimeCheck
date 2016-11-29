package com.negod.timecheck.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.event.Event;
import com.negod.timecheck.event.EventHandler;
import com.negod.timecheck.event.events.GenericObjectEvents;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class AddProjectController extends EventHandler implements Initializable {

    @FXML
    TextField nameTextField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("Event recieved in AddProjectController, Ignoring");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Project project = new Project(nameTextField.getText(), 1);
        super.sendEvent(GenericObjectEvents.CREATE, project);
    }

}
