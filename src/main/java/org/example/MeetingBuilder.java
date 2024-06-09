package org.example;

/*
    MeetingBuilder enforces an additional constraint: for Appointment
    that is a meeting, both start and end dates must be specified.
 */
public class MeetingBuilder extends AppointmentBuilder {
    public Appointment getAppointment() throws InformationRequiredException {
        try {
            super.getAppointment();
        } finally {
            if(appointment.getEndDate() == null) {
                requiredElements += END_DATE_REQUIRED;
            }

            if(requiredElements > 0) {
                throw new InformationRequiredException(requiredElements);
            }
        }
        return appointment;
    }
}
