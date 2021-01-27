package net.morimori.mildomintegration.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class MildomEvent extends Event {
    private final String message;

    public MildomEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
