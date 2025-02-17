package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class SellerProfileController implements Initializable{

    @FXML
    private Button switchToBuyingBtn;

    @FXML
    private Button soledPropertiesBtn;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button myPropertiesButton;

    @FXML
    private Button profileBtn;

    @FXML
    private Button sellPropertyBtn;

    @FXML
    private Button saveProfileBtn;

    @FXML
    private PasswordField passwordfeild;

    @FXML
    private TextField lastNameFeild;

    @FXML
    private TextField ageFeild;

    @FXML
    private TextField emailFeild;

    @FXML
    private TextField firstNameFeild;

    @FXML
    void dashboard(ActionEvent event) throws IOException {

	App.setRoot("SellerDashboard");
    }

    @FXML
    void myProperty(ActionEvent event) throws IOException {
	App.setRoot("SellerListings");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
	App.setRoot("SellerEditProfile");
    }

    @FXML
    void sellProperty(ActionEvent event) throws IOException {

	App.setRoot("AddProperty");
    }

    @FXML
    void soldProperties(ActionEvent event) throws IOException {
	App.setRoot("SoldProperties");
    }

    @FXML
    void saveProfile(ActionEvent event) throws SQLException {

	LoginController controller = new LoginController();
	int id = Integer.parseInt(controller.getId());

	String firstName = firstNameFeild.getText().toString();
	String lastName = lastNameFeild.getText().toString();
	int age = Integer.parseInt(ageFeild.getText().toString());
	String email = emailFeild.getText().toString();
	String password = passwordfeild.getText().toString();

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();



	String query = "update users set first_name=?, last_name=?, email=?, age=?, role=?,password=? where id=?";

	String query1 = "update user_details set email=?, password=?, role=? where id=?";

	PreparedStatement preparedStmt = connection.prepareStatement(query);

	PreparedStatement preparedStmt1 = connection.prepareStatement(query1);


	preparedStmt1.setString(1, email);
	preparedStmt1.setString(2, password);
	preparedStmt1.setString(3, "user");
	preparedStmt1.setInt(4, id);

	preparedStmt.setString(1, firstName);
	preparedStmt.setString(2, lastName);
	preparedStmt.setString(3, email);
	preparedStmt.setInt(4, age);
	preparedStmt.setString(5, "user");
	preparedStmt.setString(6, password);
	preparedStmt.setInt(7, id);

	preparedStmt.executeUpdate();
	preparedStmt1.executeUpdate();
	connection.close();


	Alert a = new Alert(AlertType.NONE);
	 a.setAlertType(AlertType.INFORMATION);

         // set content text
         a.setContentText("Profile Edited Successful!\n Your UserName is "+firstName+"_"+lastName+"\n Password is "+password);

         // show the dialog
         a.show();
    }

    @FXML
    void switchToBuying(ActionEvent event) throws IOException {
	App.setRoot("UserDashboard");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

	LoginController controller = new LoginController();
	User user = controller.getUser();

	firstNameFeild.setText(user.getFirstName());
	lastNameFeild.setText(user.getLastName());
	ageFeild.setText(Integer.toString(user.getAge()));
	emailFeild.setText(user.getEmail());
	passwordfeild.setText(user.getPassword());

    }


}
