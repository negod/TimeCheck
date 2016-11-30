package com.negod.timecheck.controller;

import com.negod.timecheck.LabelSetter;
import com.negod.timecheck.utils.ProgresBarHandler;
import com.negod.timecheck.database.dao.ProjectDao;
import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.event.Event;
import com.negod.timecheck.event.EventHandler;
import com.negod.timecheck.event.events.GenericObjectEvents;
import com.negod.timecheck.event.exceptions.DialogException;
import com.negod.timecheck.event.exceptions.TypeCastException;
import com.negod.timecheck.event.events.TimerEvent;
import com.negod.timecheck.timer.TimeCheckTimer;
import com.negod.timecheck.utils.ComboBoxFactory;
import com.negod.timecheck.utils.ComboBoxHandler;
import com.negod.timecheck.utils.DialogFactory;
import com.negod.timecheck.utils.TableViewFactory;
import com.negod.timecheck.utils.TableViewHandler;
import com.negod.timecheck.utils.exceptions.UtilHandlerBuilderException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TodayTabController extends EventHandler implements Initializable {

    @FXML
    private Label todayDateLabel;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Label overTimeLabel;
    @FXML
    private Label breakTimeLabel;

    @FXML
    private Button startDayButton;
    @FXML
    private Button breakButton;

    @FXML
    private ProgressBar workDayProgressBar;

    @FXML
    private ComboBox projectCombo;

    @FXML
    TableView projectsTable;

    private final Integer WORKDAY = 1;

    private TimeCheckTimer timer = new TimeCheckTimer();
    private ProjectDao projectDao;
    private TableViewHandler tableHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            projectDao = new ProjectDao();

        } catch (DaoException ex) {
            log.error("Error when initializing DAO: {}", ex.getMessage());
        }

        try {
            ComboBoxHandler comboBoxHandler = ComboBoxFactory.getNewInstance()
                    .setComboBox(projectCombo)
                    .setDao(projectDao)
                    .setFieldName("name").build();

            tableHandler = TableViewFactory.getNewInstance()
                    .setDao(projectDao)
                    .setTableView(projectsTable).build();

        } catch (UtilHandlerBuilderException ex) {
            Logger.getLogger(TodayTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

        LabelSetter.setLabelValue(LocalDate.now().format(DateTimeFormatter.ISO_DATE), todayDateLabel);
        LabelSetter.setLabelValueAsTimeFromMillis(0L, totalTimeLabel);
        LabelSetter.setLabelValueAsTimeFromMillis(0L, overTimeLabel);
        LabelSetter.setLabelValueAsTimeFromMillis(0L, breakTimeLabel);

        ProgresBarHandler.setProgress(0.0, workDayProgressBar);

        super.listenForEvent(TimerEvent.NEW_TIME);
        super.listenForEvent(TimerEvent.BREAK_TIME);
    }

    @FXML
    private void handleStartDayButton(ActionEvent event) {
        Boolean started = timer.isStarted() ? timer.resetTimer() : timer.startTimer(1);
        LabelSetter.setLabelValue(started ? "Stop workday" : "Start workday", startDayButton);
    }

    @FXML
    private void handleBreakButton(ActionEvent event) {
        Boolean paused = timer.isPaused() ? timer.resumeTimer() : timer.pauseTimer();
        LabelSetter.setLabelValue(paused ? "Stop break" : "Start break", breakButton);
    }

    public Double getWorkdayPercentage(Long currentMillis) {
        return currentMillis.doubleValue() / getMillisecondsFromHours(WORKDAY);
    }

    public Long getOverTimeInMillis(Long currentTime, Long workHours) {
        return currentTime > workHours ? currentTime - workHours : 0L;
    }

    public Long getMillisecondsFromHours(Integer hours) {
        return TimeUnit.HOURS.toMillis(hours);
    }

    @Override
    public void onEvent(final Event event) {
        try {
            if (event.isOfType(TimerEvent.NEW_TIME)) {
                Long time = event.getData().getAsLong().get();
                LabelSetter.setLabelValueAsTimeFromMillis(time, totalTimeLabel);
                Long overTime = getOverTimeInMillis(time, getMillisecondsFromHours(WORKDAY));
                LabelSetter.setLabelValueAsTimeFromMillis(overTime, overTimeLabel);
                ProgresBarHandler.setProgress(getWorkdayPercentage(time), workDayProgressBar);
            } else if (event.isOfType(TimerEvent.BREAK_TIME)) {
                Long time = event.getData().getAsLong().get();
                LabelSetter.setLabelValueAsTimeFromMillis(time, breakTimeLabel);
            }
        } catch (TypeCastException ex) {
            Logger.getLogger(TodayTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openAddProject(ActionEvent event) throws DialogException, DaoException {
        Optional<Project> showModalAndWait = DialogFactory.getNewInstance()
                .setDao(projectDao)
                .setEntity(null)
                .build().showModalAndWait();
    }

    @FXML
    private void openUpdateProject(ActionEvent event) throws DialogException, DaoException {
        Optional<Project> showModalAndWait = DialogFactory.getNewInstance()
                .setDao(projectDao)
                .setEntity(tableHandler.getSelectedValue())
                .build().showModalAndWait();
    }

    @FXML
    private void deleteProject(ActionEvent event) throws DialogException {
        super.sendEvent(GenericObjectEvents.DELETE, tableHandler.getSelectedValue());
    }
}
