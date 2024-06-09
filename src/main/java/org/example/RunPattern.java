package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RunPattern {
    private static Calendar dateCreator = Calendar.getInstance();

    public static void main(String[] args) {
        Appointment appt = null;
        System.out.println("Example showing use of Builder Pattern");
        System.out.println("---- ---- ---- ---- ----");
        System.out.println("Creating a Scheduler");
        Scheduler pimScheduler = new Scheduler();

        System.out.println("Creating an AppointmentBuilder");
        AppointmentBuilder apptBuilder = new AppointmentBuilder();

        System.out.println("Creating an Appointment with AppointmentBuilder");
        try {
            appt = pimScheduler.createAppointment(apptBuilder, createDate(2024, 6, 15, 12, 30), null,
                    "BC Convention", new LocationImpl("San Francisco, CA"), createAttendees(4));
            System.out.println("Successfully created an Appointment");
            System.out.println("Appointment Information:");
            System.out.println(appt);
            System.out.println();
        } catch (InformationRequiredException exc) {
            printExceptions(exc);
        }

        System.out.println("Creating an MeetingBuilder");
        AppointmentBuilder mtgBuilder = new MeetingBuilder();
        System.out.println("Creating an Appointment with MeetingBuilder, without providing an end date.");
        try {
            appt = pimScheduler.createAppointment(mtgBuilder, createDate(2024, 6, 15, 12, 30), null,
                    "BC Convention", new LocationImpl("San Francisco, CA"), createAttendees(4));
            System.out.println("Successfully created an Appointment");
            System.out.println("Appointment Information:");
            System.out.println(appt);
            System.out.println();
        } catch (InformationRequiredException exc) {
            printExceptions(exc);
        }

        System.out.println();
        System.out.println("Creating an Appointment with MeetingBuilder, this time providing an end date.");
        try {
            appt = pimScheduler.createAppointment(mtgBuilder, createDate(2024, 6, 15, 12, 30),
                    createDate(2024, 6, 18, 12, 30), "DLT Convention",
                    new LocationImpl("Austin, TX"), createAttendees(2));
            System.out.println("Successfully created an Appointment");
            System.out.println("Appointment Information:");
            System.out.println(appt);
            System.out.println();
        } catch (InformationRequiredException exc) {
            printExceptions(exc);
        }
        System.out.println("---- ---- ---- ---- ----");
    }

    public static Date createDate(int year, int month, int day, int hour, int min) {
        dateCreator.set(year, month, day, hour, min);
        return dateCreator.getTime();
    }

    public static ArrayList createAttendees(int num) {
        ArrayList group = new ArrayList();
        for(int i = 0; i < num; i++) {
            group.add(new ContactImpl("John", getLastName(i), "Employee", "USTM Corporation"));
        }
        return group;
    }

    public static String getLastName(int index) {
        String name = switch (index % 6) {
            case 0 -> "Marvin";
            case 1 -> "Sterling";
            case 2 -> "Hugo";
            case 3 -> "Mason";
            case 4 -> "McCoy";
            case 5 -> "Quinn";
            default -> "";
        };
        return name;
    }

    public static void printExceptions(InformationRequiredException exc) {
        int statusCode = exc.getInformationRequired();
        System.out.println("Unable to create Appointment: additional information is required.");

        if((statusCode & InformationRequiredException.START_DATE_REQUIRED) > 0) {
            System.out.println(" A start date is required to complete this appointment.");
        }

        if((statusCode & InformationRequiredException.END_DATE_REQUIRED) > 0) {
            System.out.println(" A end date is required to complete this appointment.");
        }

        if((statusCode & InformationRequiredException.DESCRIPTION_REQUIRED) > 0) {
            System.out.println(" A description is required to complete this appointment.");
        }

        if((statusCode & InformationRequiredException.LOCATION_REQUIRED) > 0) {
            System.out.println(" A location is required to complete this appointment.");
        }

        if((statusCode & InformationRequiredException.ATTENDEE_REQUIRED) > 0) {
            System.out.println(" At least one attendee is required to complete this appointment.");
        }
        System.out.println();
    }
}