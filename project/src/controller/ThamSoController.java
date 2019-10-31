package controller;

import java.sql.SQLException;

import BUS.ThamSoBUS;
import DTO.ThamSoDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class ThamSoController {

	@FXML
	Spinner<Integer> snTiLeVAT;
	@FXML
	Spinner<Integer> snTiLeCoc;

	public void initialize(ThamSoDTO thamSo) {
		snTiLeVAT.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
		snTiLeCoc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));

		snTiLeVAT.focusedProperty().addListener((obs, oldValue, newValue) -> snTiLeVAT.increment(0));
		snTiLeCoc.focusedProperty().addListener((obs, oldValue, newValue) -> snTiLeCoc.increment(0));

		snTiLeVAT.getValueFactory().setValue((int) (thamSo.getTiLeThueVAT() * 100));
		snTiLeCoc.getValueFactory().setValue((int) (thamSo.getTiLeTienCoc() * 100));	}

	public void handleXacNhan(ActionEvent e) {
		try {
			ThamSoDTO thamSo = new ThamSoDTO((float) snTiLeVAT.getValue() / 100, (float) snTiLeCoc.getValue() / 100);

			if (ThamSoBUS.updateThamSo(thamSo)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Sửa các tham số thành công!");
				alert.showAndWait();
				MainController controller = (MainController) snTiLeVAT.getScene().getUserData();
				controller.loadTableThamSo();
				Stage stage = (Stage) snTiLeVAT.getScene().getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Sửa tham số thất bại!");
				alert.setContentText("Vui lòng nhập lại các giá trị tham số!");
				alert.showAndWait();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Sửa các tham số thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage) snTiLeVAT.getScene().getWindow();
		stage.close();
	}
}
