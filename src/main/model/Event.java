package model;

/**
 * Represents an activity tracker event.
 */
public class Event {
    private String description;

    /**
     * Creates an event with the given description
     * @param description  a description of the event
     */
    public Event(String description) {
        this.description = description;
    }

    /**
     * Gets the description of this event.
     * @return  the description of the event
     */
    public String getDescription() {
        return description;
    }
}
