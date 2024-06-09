package org.example;

import java.util.ArrayList;
import java.util.Date;

/*
    Scheduler class makes calls to AppointmentBuilder, managing the
    creation process through createAppointment method.

    Returns complete Appointment object to its caller.
 */
public class Scheduler {
    public Appointment createAppointment(AppointmentBuilder builder, Date startDate, Date endDate,
                                         String description, Location location, ArrayList attendees)
            throws InformationRequiredException {
        if(builder == null) {
            builder = new AppointmentBuilder();
        }

        builder.buildAppointment();
        builder.buildDates(startDate, endDate);
        builder.buildDescription(description);
        builder.buildAttendees(attendees);
        builder.buildLocation(location);

        return builder.getAppointment();
    }

}
