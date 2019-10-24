package custom.control;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class ListRoomDetailPane {

	private List<RoomDetailPane> panes;
	private RoomDetailPane curPane;

	public ListRoomDetailPane(List<String> dsMaPhong) {
		panes = new ArrayList<RoomDetailPane>();
		curPane = null;

		dsMaPhong.stream().forEach(maPhong -> {
			RoomDetailPane pane = new RoomDetailPane(maPhong);
			if (curPane == null) {
				curPane = pane;
				curPane.changeBackgroundColor("lightblue");
			}

			pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				curPane.changeBackgroundColor("transparent");
				curPane = pane;
				pane.changeBackgroundColor("lightblue");
			});
			panes.add(pane);
		});
	}

	public List<RoomDetailPane> getPanes() {
		return panes;
	}
}