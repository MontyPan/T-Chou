package us.dontcareabout.chou.ticTacToe;

import us.dontcareabout.chou.ticTacToe.InputEvent.InputHandler;
import us.dontcareabout.chou.ticTacToe.eventBus.Event;
import us.dontcareabout.chou.ticTacToe.eventBus.EventBus;

public class EventCenter {
	private static EventBus eventBus = new EventBus();

	public static void fireEvent(Event<?> e) {
		eventBus.fire(e);
	}

	public static void addInputHandler(InputHandler h) {
		eventBus.addHandler(InputEvent.class, h);
	}
}
