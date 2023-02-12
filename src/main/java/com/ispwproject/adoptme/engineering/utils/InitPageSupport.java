package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Observer;

public class InitPageSupport {
    private InitPageSupport() {
        //private constructor
    }

    public static void initAppointmentsPage(Observer observer) {
        ShowRequestsController showRequestsController = new ShowRequestsController();
        try {
            showRequestsController.getRequestList(observer);
        } catch (NotFoundException | DateFormatException | TimeFormatException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }
}
