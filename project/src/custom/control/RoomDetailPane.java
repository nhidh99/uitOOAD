package custom.control;

import java.io.File;

import DTO.PhongDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class RoomDetailPane extends BorderPane {
	
	private PhongDTO phong;
	
	public RoomDetailPane(PhongDTO phong) {
		File file = new File("src/application/room.png");	
		ImageView imgView = new ImageView();
		imgView.setImage(new Image(file.toURI().toString()));
		imgView.setFitWidth(80);
		imgView.setFitHeight(80);
		
		Label label = new Label("PHÒNG " + phong.getMaPhong());
		label.setMaxWidth(Double.MAX_VALUE);
		label.setAlignment(Pos.CENTER);
		label.setStyle(
				"-fx-padding: 10 0 0 0;"
				+ "-fx-font-size: 14px;"
				+ "-fx-font-weight: bold;");
		
		this.phong = phong;
		this.setPadding(new Insets(10, 15, 10, 15));
		this.setCenter(imgView);
		this.setBottom(label);
	}
	
	public PhongDTO getPhong() {
		return phong;
	}

	public void changeBackgroundColor(String color) {
		this.setStyle("-fx-background-color: " + color);
	}
}