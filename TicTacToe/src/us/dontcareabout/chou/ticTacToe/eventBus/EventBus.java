package us.dontcareabout.chou.ticTacToe.eventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventBus {
	private final HashMap<Class<?>, List<?>> map = new HashMap<>();

	public <H extends Handler> void addHandler(Class<?> clazz, H h) {
		@SuppressWarnings("unchecked")
		List<H> handlerList = (List<H>)map.get(clazz);

		if (handlerList == null) {
			handlerList = new ArrayList<>();
			map.put(clazz, handlerList);
		}

		handlerList.add(h);
	}

	public <H extends Handler> void fire(Event<H> e) {
		@SuppressWarnings("unchecked")
		List<H> handlerList = (List<H>) map.get(e.getClass());

		for(H h : handlerList) {
			e.dispatch(h);
		}
	}
}
