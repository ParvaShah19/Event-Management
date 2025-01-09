package Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

abstract class EventItem {
    protected String name;

    public EventItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method to get details of the event item
    public abstract String getDetails();
}

class Event extends EventItem {
    private String eventDate;
    private List<Attendee> attendees;

    public Event(String eventName, String eventDate) {
        super(eventName);
        this.eventDate = eventDate;
        this.attendees = new ArrayList<>();
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public boolean removeAttendee(Attendee attendee) {
        return attendees.remove(attendee);
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    @Override
    public String getDetails() {
        return "Event Name: " + name + ", Date: " + eventDate + ", Attendees: " + attendees.size();
    }

	public void setName(String newEventName) {
		// TODO Auto-generated method stub
		
	}

	public Attendee findAttendeeByName(String attendeeName) {
		// TODO Auto-generated method stub
		return null;
	}
}

class Attendee extends EventItem {
    public Attendee(String attendeeName) {
        super(attendeeName);
    }

    @Override
    public String getDetails() {
        return "Attendee Name: " + name;
    }
}

class EventManagementSystem {
    private List<Event> events;
	private String name;

    public EventManagementSystem() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void displayEvents() {
        for (Event event : events) {
            System.out.println(event.getDetails());
        }
    }

    public String getEventsAsString() {
        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
            sb.append(event.getDetails()).append("\n");
        }
        return sb.toString();
    }
    
    public void addAttendeeToEvent(String eventName, Attendee attendee) {
        Event event = findEventByName(eventName);
        if (event != null) {
            event.addAttendee(attendee);
            System.out.println(attendee.getName() + " added to event: " + event.getName());
        } else {
            System.out.println("Event not found: " + eventName);
        }
    }

    
    private Event findEventByName(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null; // Event not found
    }

	public void removeEvent(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                events.remove(event);
                System.out.println("Event removed: " + eventName);
                return; // exit the loop once the event is removed
            }
        }
        System.out.println("Event not found: " + eventName);
    }

	
	public void removeAttendeeFromEvent(String eventName, String attendeeName) {
	    Event event = findEventByName(eventName);
	    if (event != null) {
	        Attendee attendeeToRemove = event.findAttendeeByName(attendeeName);
	        if (attendeeToRemove != null) {
	            event.removeAttendee(attendeeToRemove);
	            System.out.println("Attendee removed: " + attendeeName + " from event: " + eventName);
	        } else {
	            System.out.println("Attendee not found in event: " + eventName);
	        }
	    } else {
	        System.out.println("Event not found: " + eventName);
	    }
	}
	
	public void editEventDetails(String eventName, String newEventName, String newEventDate) {
        Event event = findEventByName(eventName);
        if (event != null) {
            event.setName(newEventName);
            event.setEventDate(newEventDate);
            System.out.println("Event details updated successfully.");
        } else {
            System.out.println("Event not found: " + eventName);
        }
    }
	
	public String getAllAttendeesAsString() {
        StringBuilder allAttendeesString = new StringBuilder();
        allAttendeesString.append("List of all attendees:\n");
        for (Event event : events) {
            List<Attendee> attendees = event.getAttendees();
            for (Attendee attendee : attendees) {
                allAttendeesString.append(attendee.getName()).append("\n");
            }
        }
        return allAttendeesString.toString();
    }
	
	public Attendee findAttendeeByName(String attendeeName, Attendee[] attendees) {
        for (Attendee attendee : attendees) {
            if (attendee.getName().equals(attendeeName)) {
                return attendee;
            }
        }
        return null; // Attendee not found
    }


    public void editEventDetails(Event event, String newEventName, String newEventDate) {
        event.name = newEventName;
        event.setEventDate(newEventDate);
        System.out.println("Event details updated successfully.");
    }
    
    public String getAttendeesOfEventAsString(String eventName) {
        Event event = findEventByName(eventName);
        if (event != null) {
            List<Attendee> attendees = event.getAttendees();
            StringBuilder attendeesString = new StringBuilder();
            attendeesString.append("Attendees of ").append(eventName).append(":\n");
            for (Attendee attendee : attendees) {
                attendeesString.append(attendee.getName()).append("\n");
            }
            return attendeesString.toString();
        } else {
            return "Event not found: " + eventName;
        }
    }

    public void listAttendeesOfEvent(Event event) {
        System.out.println("Attendees of event '" + event.getName() + "':");
        for (Attendee attendee : event.getAttendees()) {
            System.out.println(attendee.getDetails());
        }
    }

    public void listAllAttendees() {
        System.out.println("List of all attendees:");
        for (Event event : events) {
            for (Attendee attendee : event.getAttendees()) {
                System.out.println(attendee.getDetails());
            }
        }
    }
}

public class management {
    public static void main(String[] args) {
        EventManagementSystem ems = new EventManagementSystem();
        JFrame frame = new JFrame("Event Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Grid layout for buttons

        // Create buttons
        JButton addEventButton = new JButton("Add Event");
        JButton displayEventsButton = new JButton("Display Events");
        JButton addAttendeeButton = new JButton("Add Attendee to Event");
        JButton removeEventButton = new JButton("Remove Event");
        JButton removeAttendeeButton = new JButton("Remove Attendee from Event");
        JButton editEventButton = new JButton("Edit Event Details");
        JButton listAttendeesButton = new JButton("List Attendees of Event");
        JButton listAllAttendeesButton = new JButton("List All Attendees");

        // Add buttons to the panel
        mainPanel.add(addEventButton);
        mainPanel.add(displayEventsButton);
        mainPanel.add(addAttendeeButton);
        mainPanel.add(removeEventButton);
        mainPanel.add(removeAttendeeButton);
        mainPanel.add(editEventButton);
        mainPanel.add(listAttendeesButton);
        mainPanel.add(listAllAttendeesButton);

        // Add panel to frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Action listeners for buttons
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
                String eventDate = JOptionPane.showInputDialog(frame, "Enter Event Date:");
                if (eventName != null && eventDate != null) {
                    ems.addEvent(new Event(eventName, eventDate));
                    JOptionPane.showMessageDialog(frame, "Event added successfully.");
                }
            }
        });

        displayEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, ems.getEventsAsString(), "Events", JOptionPane.PLAIN_MESSAGE);
            }
        });

        addAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
                if (eventName != null) {
                    String attendeeName = JOptionPane.showInputDialog(frame, "Enter Attendee Name:");
                    if (attendeeName != null) {
                        ems.addAttendeeToEvent(eventName, new Attendee(attendeeName));
                        JOptionPane.showMessageDialog(frame, "Attendee added to event successfully.");
                    }
                }
            }
        });

        removeEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name to remove:");
                if (eventName != null) {
                    ems.removeEvent(eventName);
                    JOptionPane.showMessageDialog(frame, "Event removed successfully.");
                }
            }
        });

        removeAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
                if (eventName != null) {
                    String attendeeName = JOptionPane.showInputDialog(frame, "Enter Attendee Name to remove:");
                    if (attendeeName != null) {
                        ems.removeAttendeeFromEvent(eventName, attendeeName);
                        JOptionPane.showMessageDialog(frame, "Attendee removed from event successfully.");
                    }
                }
            }
        });

        editEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name to edit:");
                if (eventName != null) {
                    String newEventName = JOptionPane.showInputDialog(frame, "Enter New Event Name:");
                    String newEventDate = JOptionPane.showInputDialog(frame, "Enter New Event Date:");
                    if (newEventName != null && newEventDate != null) {
                        ems.editEventDetails(eventName, newEventName, newEventDate);
                        JOptionPane.showMessageDialog(frame, "Event details updated successfully.");
                    }
                }
            }
        });

        listAttendeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name to list attendees:");
                if (eventName != null) {
                    JOptionPane.showMessageDialog(frame, ems.getAttendeesOfEventAsString(eventName), "Attendees of " + eventName, JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        listAllAttendeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, ems.getAllAttendeesAsString(), "All Attendees", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}