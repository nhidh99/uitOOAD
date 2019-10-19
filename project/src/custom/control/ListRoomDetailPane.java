package custom.control;

import DTO.PhongDTO;

import java.util.List;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ListRoomDetailPane {
	
	private List<RoomDetailPane> panes;
	private RoomDetailPane curPane;
	
	public ListRoomDetailPane(List<PhongDTO> dsPhong) {
		panes = new ArrayList<RoomDetailPane>();
		curPane = null;
		
		for (PhongDTO phong : dsPhong) {
			RoomDetailPane pane = new RoomDetailPane(phong);
			if (curPane == null) {
				curPane = pane;
				curPane.changeBackgroundColor("lightblue");
			}
			
			pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					curPane.changeBackgroundColor("transparent");
					curPane = pane;
					pane.changeBackgroundColor("lightblue");
				}
			});	
			panes.add(pane);
		}
	}
	
	public List<RoomDetailPane> getPanes() {
		return panes;
	}
}