package lk.ijse.gdse66.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse66.hibernate.entity.Customer;
import lk.ijse.gdse66.hibernate.dao.CustomerDAO;

import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : ORM_GDSE66
 * @since : 7:25 AM - 8/7/2023
 **/
public class CustomerFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtSalary;
    private CustomerDAO customerDAO = new CustomerDAO();
    private Customer customer = new Customer();

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {
        customer.setId(Integer.parseInt(txtId.getText()));
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));

        boolean isSaved = customerDAO.saveCustomer(customer);
        if (isSaved == true) {
            new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());

        Customer isSearched = customerDAO.searchCustomer(id);
        if (isSearched != null) {
            txtName.setText(String.valueOf(isSearched.getName()));
            txtAddress.setText(String.valueOf(isSearched.getAddress()));
            txtSalary.setText(String.valueOf(isSearched.getSalary()));
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent actionEvent) {
        customer.setId(Integer.parseInt(txtId.getText()));
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));

        boolean isUpdated = customerDAO.updateCustomer(customer);
        if (isUpdated == true) {
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {
        customer.setId(Integer.parseInt(txtId.getText()));

        boolean isDeleted = customerDAO.deleteCustomer(customer);
        if (isDeleted == true) {
            new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

    @FXML
    private void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hibernate/view/dashboard_form.fxml"))));
    }
}
