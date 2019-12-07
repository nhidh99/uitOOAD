package custom.control;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class RoomDetailPane extends BorderPane {
	
	private String maPhong;
	
	public RoomDetailPane(String maPhong) {
		File file = new File("room.png");	
		ImageView imgView = new ImageView();
		imgView.setImage(new Image(file.toURI().toString()));
		imgView.setFitWidth(75);
		imgView.setFitHeight(75);
		
		Label label = new Label("P." + maPhong);
		label.setMaxWidth(Double.MAX_VALUE);
		label.setAlignment(Pos.CENTER);
		label.setStyle(
				"-fx-padding: 10 0 0 0;"
				+ "-fx-font-size: 18px;"
				+ "-fx-font-weight: bold;");
		
		this.maPhong = maPhong;
		this.setPadding(new Insets(10, 15, 10, 15));
		this.setCenter(imgView);
		this.setBottom(label);
	}
	
	public String getMaPhong() {
		return maPhong;
	}

	public void changeBackgroundColor(String color) {
		this.setStyle("-fx-background-color: " + color);
	}
}