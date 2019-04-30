package us.dontcareabout.chou.ticTacToe;

import us.dontcareabout.chou.ticTacToe.InputEvent.InputHandler;
import us.dontcareabout.chou.ticTacToe.eventBus.Event;
import us.dontcareabout.chou.ticTacToe.eventBus.Handler;

public class InputEvent extends Event<InputHandler>{
	public final int value;

	public InputEvent(int value) {
		this.value = value;
	}

	@Override
	protected void dispatch(InputHandler handler) {
		handler.onInput(this);
	}

	public interface InputHandler extends Handler {
		public void onInput(InputEvent event);
	}
}
