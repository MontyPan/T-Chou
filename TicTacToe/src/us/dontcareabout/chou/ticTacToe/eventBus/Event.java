package us.dontcareabout.chou.ticTacToe.eventBus;

public abstract class Event<H extends Handler> {
	protected abstract void dispatch(H handler);
}
