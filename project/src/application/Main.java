package application;
	
import DTO.NhanVienDTO;
import javafx.application.Application;
import javafx.stage.Stage;
import controller.LoginController;
import controller.MainController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;


public class Main extends Application {
	private NhanVienDTO nhanVien;
	private Parent root;
	private Stage window = new Stage();
	public Parent createScene() throws Exception {
		gotoLogin();
		return root;
	}
	
	public NhanVienDTO getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVienDTO nhanVien) {
		this.nhanVien = nhanVien;
		
		try {
			gotoMainApp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi phân quyền");
			e.printStackTrace();
		}
	}
	
	private void gotoLogin() throws Exception {
            LoginController login =
                (LoginController)replaceSceneContent("loginForm.fxml");
            login.setApp(this);
    }
	private void gotoMainApp() throws Exception {
		MainController mainapp = (MainController)replaceSceneContent("main.fxml");
		mainapp.setApp(this);
	}
	public void logOut() throws Exception {
		gotoLogin();
		System.out.println("Logout!");
	}
	
	private Initializable replaceSceneContent(String fxml) throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
	        loader.setLocation(Main.class.getResource(fxml));
	        loader.setBuilderFactory(new JavaFXBuilderFactory());
	        root = (Parent) loader.load();
	        switch(fxml) {
	        case "main.fxml":
	        	root.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
	        	window.setWidth(1280);
	        	window.setHeight(800);
	        	break;
	        case "loginForm.fxml":
	        	window.setWidth(600);
	        	window.setHeight(400);
	        	break;
	        default:
	        	break;
	        }
	        window.setScene(new Scene(root));
	        window.centerOnScreen();
	        window.sizeToScene();
	        return (Initializable)loader.getController();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			gotoLogin();
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể mở CSDL!");
			alert.showAndWait();
		}
		launch(args);
	}
}
