package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.HDPtckDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HDPtckController implements Initializable {
	@FXML
	private ComboBox<String> cbbPTCK;
	@FXML
	private TextField tfNoiDung;
	@FXML
	private TextField tfTriGia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbbPTCK.setItems(FXCollections.observableArrayList("Phụ thu", "Chiết khấu"));
		cbbPTCK.getSelectionModel().selectFirst();
	}

	public void handleXacNhan(ActionEvent e) {
		Integer triGia = (cbbPTCK.getSelectionModel().getSelectedIndex() == 0 ? 1 : -1) * Integer.valueOf(tfTriGia.getText());
		HDPtckDTO hdPtck = new HDPtckDTO(tfNoiDung.getText(), triGia);		
		MainController controller = (MainController) cbbPTCK.getScene().getUserData();
		controller.insertHDPtck(hdPtck);
		Stage stage = (Stage) cbbPTCK.getScene().getWindow();
		stage.close();
	}
	
	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage) cbbPTCK.getScene().getWindow();
		stage.close();
	}
}
