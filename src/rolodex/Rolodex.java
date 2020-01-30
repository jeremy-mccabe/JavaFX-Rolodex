/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rolodex;

import java.util.Comparator;
import java.util.LinkedList;
import rolodex.Enums.PhoneTypeEnum;
import rolodex.PhoneNumberRecord;
import rolodex.RolodexRecord;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javax.swing.tree.TreeNode;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 *
 * @author jeremy
 */
public class Rolodex extends Application
{

    @Override
    public void start(Stage primaryStage)
    {

        //SAMPLE ENTRIES & TREEITEMS:
        PhoneNumberRecord num1 = new PhoneNumberRecord(PhoneTypeEnum.Home, "12341234");
        PhoneNumberRecord num2 = new PhoneNumberRecord(PhoneTypeEnum.NotSelected, "12341234");
        PhoneNumberRecord num3 = new PhoneNumberRecord(PhoneTypeEnum.Work, "12341234");
        PhoneNumberRecord num4 = new PhoneNumberRecord(PhoneTypeEnum.Personal, "12341234");
        LinkedList<PhoneNumberRecord> ll = new LinkedList<>();
        ll.add(num1);
        ll.add(num2);
        ll.add(num3);
        ll.add(num4);
        //ll.addAll(num1, num2, num3, num4);
        RolodexRecord a = new RolodexRecord("Jeremy", "McCabe", "A", new LinkedList<>(ll));
        //a.setPhoneNumberRecordList(num1);
        //a.setPhoneNumberRecordList(null);
        RolodexRecord b = new RolodexRecord("Jane", "Doe", "Anne");
        RolodexRecord c = new RolodexRecord("Adam", "Parker", "H");
        RolodexRecord d = new RolodexRecord("Tom", "Baker", "Samuel");

        TreeItem<RolodexRecord> ti1 = new TreeItem<>(a);
        TreeItem<RolodexRecord> ti2 = new TreeItem<>(b);
        TreeItem<RolodexRecord> ti3 = new TreeItem<>(c);
        TreeItem<RolodexRecord> ti4 = new TreeItem<>(d);

        TreeItem<RolodexRecord> tiRoot = new TreeItem<>(new RolodexRecord());
        tiRoot.getChildren().setAll(ti1, ti2, ti3, ti4);

        /**
         * Main GUI
         */
        // MENU:
        // main bar:
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);
        // file menu:
        Menu file = new Menu("File");
        MenuItem fileItem1 = new MenuItem("Exit");
        file.getItems().add(fileItem1);
        // edit menu:
        Menu edit = new Menu("Edit");
        MenuItem editItem1 = new MenuItem("Add");
        MenuItem editItem2 = new MenuItem("Modify");
        MenuItem editItem3 = new MenuItem("Delete");
        edit.getItems().add(editItem1);
        edit.getItems().add(editItem2);
        edit.getItems().add(editItem3);
        // adding children nodes:
        menuBar.getMenus().addAll(file, edit);

        // TREE TABLE VIEW:
        TreeTableView<RolodexRecord> ttv = new TreeTableView<>();
        ObservableList list = ttv.getChildrenUnmodifiable();
        int entryCount = tiRoot.getChildren().size();
        // last col:
        TreeTableColumn<RolodexRecord, String> col1 = new TreeTableColumn<>("Last Name");
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<>("getLastName"));
        ttv.getColumns().add(col1);
        // first col:
        TreeTableColumn<RolodexRecord, String> col2 = new TreeTableColumn<>("First Name");
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<>("getFirstName"));
        ttv.getColumns().add(col2);
        // middle col:
        TreeTableColumn<RolodexRecord, String> col3 = new TreeTableColumn<>("Middle Name");
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<>("getMiddleName"));
        ttv.getColumns().add(col3);
        // number count col:
        TreeTableColumn<RolodexRecord, String> col4 = new TreeTableColumn<>("Phone Number Count");
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<>("getNumberCount"));
        ttv.getColumns().add(col4);
        // alignment:
        col1.prefWidthProperty().bind(ttv.widthProperty().divide(4));
        col2.prefWidthProperty().bind(ttv.widthProperty().divide(4));
        col3.prefWidthProperty().bind(ttv.widthProperty().divide(4));
        col4.prefWidthProperty().bind(ttv.widthProperty().divide(4));
                // TREETABLEVIEW CALLBACKS:
        // last name col:
        col1.setCellValueFactory(new Callback<CellDataFeatures<RolodexRecord, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(CellDataFeatures<RolodexRecord, String> param)
            {
                return new ReadOnlyStringWrapper(param.getValue().getValue().getLastName());
            }
        });
        // first name col:
        col2.setCellValueFactory((CellDataFeatures<RolodexRecord, String> param) ->
        {
            return new ReadOnlyStringWrapper(param.getValue().getValue().getFirstName());
        });
        // middle name col:
        col3.setCellValueFactory((CellDataFeatures<RolodexRecord, String> param) ->
        {
            return new ReadOnlyStringWrapper(param.getValue().getValue().getMiddleName());
        });
        // phone number count col:
        col4.setCellValueFactory((CellDataFeatures<RolodexRecord, String> param) ->
        {
            return new ReadOnlyStringWrapper(Integer.toString(param.getValue().getValue().getPhoneNumberRecordList().size()));
        });

        // BORDER & STATUS BAR
        BorderPane bottomBorder = new BorderPane();
        HBox bottomBox = new HBox();
        Text status = new Text(entryCount + " item(s)");
        bottomBox.getChildren().add(status);
        bottomBorder.setBottom(bottomBox);

        // PARENT NODE:
        BorderPane root = new BorderPane();
        root.setTop(vBox);
        root.setCenter(ttv);
        root.setBottom(bottomBorder);

        // FILE->EXIT:
        fileItem1.setOnAction(e -> Platform.exit());
        // ADD/EDIT SECONDARY STAGE:
        editItem1.setOnAction(e -> new PhoneBookStage("New Phone Book", new RolodexRecord()));
        editItem2.setOnAction(e -> new PhoneBookStage("Edit Phone Book", ti1.getValue()));
        // 

        // SCENE:
        Scene scene = new Scene(root, 800, 700);

        // STAGES:
        primaryStage.setTitle("Rolodex");
        primaryStage.setScene(scene);
        primaryStage.show();

        ttv.setRoot(tiRoot);
        ttv.setShowRoot(false);
        
        //TreeItem<RolodexRecord> selectedItem = ttv.getSelectionModel().getSelectedItem();
        //if (selectedItem != null) System.out.println(selectedItem.getValue().getFirstName());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}

// SECONDARY STAGE CLASS:
class PhoneBookStage
{

    PhoneBookStage(String header, RolodexRecord obj)
    {

        // ROOT NODE & SCENE:
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 700);

        // CHILD NODES:
        // CHILD: 1
        // text:
        Text mainText1 = new Text("Please enter Phone Book data and press OK button.");
        Text mainText2 = new Text("Press Cancel to discard all changes.");
        VBox tBox = new VBox(mainText1, mainText2);
        tBox.setPadding(new Insets(0, 0, 15, 0));
        tBox.setSpacing(5);

        // CHILD: 2
        // names pane:
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);
        Text firstText = new Text("First Name:");
        firstText.setStyle("-fx-font-weight: bold;");
        Text lastText = new Text("Last Name:");
        lastText.setStyle("-fx-font-weight: bold;");
        Text middleText = new Text("Middle Name:");
        middleText.setStyle("-fx-font-weight: bold;");
        TextField firstField = new TextField(obj.getFirstName());
        TextField lastField = new TextField(obj.getLastName());
        TextField middleField = new TextField(obj.getMiddleName());
        // expand text fields with window growth:
        HBox.setHgrow(firstField, Priority.ALWAYS);
        HBox.setHgrow(lastField, Priority.ALWAYS);
        HBox.setHgrow(middleField, Priority.ALWAYS);
        HBox row1 = new HBox(firstText, firstField);
        HBox row2 = new HBox(lastText, lastField);
        HBox row3 = new HBox(middleText, middleField);
        row1.setSpacing(50);
        row2.setSpacing(50);
        row3.setSpacing(35);
        VBox nameBox = new VBox(row1, row2, row3);
        nameBox.setSpacing(5);
        // line separators:
        Separator s1 = new Separator();
        Separator s2 = new Separator();
        // text:
        Text tableText = new Text("Phones:");
        tableText.setStyle("-fx-font-weight: bold;");
        TextFlow wrapper = new TextFlow(tableText);
        wrapper.setTextAlignment(TextAlignment.LEFT);
        // table view obj:
        TableView tv = new TableView();
        // table view cols:
        TableColumn<PhoneNumberRecord, String> col5 = new TableColumn<>("Type");
        col5.setCellValueFactory(new PropertyValueFactory("getType"));
        TableColumn<PhoneNumberRecord, String> col6 = new TableColumn<>("Number");
        col6.setCellValueFactory(new PropertyValueFactory("getNumber"));
        // add table view children:
        tv.getColumns().addAll(col5, col6);
        // table view style:
        tv.setPrefWidth(450);
        tv.setPrefHeight(400);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // modify buttons box:
        Button add = new Button("Add");
        add.setStyle("-fx-font-weight: bold;");
        Button edit = new Button("Edit");
        edit.setStyle("-fx-font-weight: bold;");
        Button delete = new Button("Delete");
        delete.setStyle("-fx-font-weight: bold;");
        HBox buttonBox = new HBox(add, edit, delete);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(0, 0, 0, 0));
        // line separator:
        Separator s3 = new Separator();
        // add children to center box:
        centerBox.getChildren().addAll(s1, nameBox, s2, wrapper, tv, buttonBox, s3);

        // CHILD: 3
        // ok-cancel buttons box:
        Button ok = new Button("OK");
        ok.setPrefWidth(80);
        Button cancel = new Button("Cancel");
        cancel.setPrefWidth(90);
        HBox confirmBox = new HBox(ok, cancel);
        confirmBox.setSpacing(10);
        confirmBox.setPadding(new Insets(20, 0, 0, 0));
        confirmBox.setAlignment(Pos.CENTER_RIGHT);

        // root node formatting:
        root.setTop(tBox);
        root.setCenter(centerBox);
        root.setBottom(confirmBox);
        root.setPadding(new Insets(15, 10, 10, 10));

        // sample instance to expand table:
        PhoneNumberRecord i = new PhoneNumberRecord(PhoneTypeEnum.NotSelected, "123456778900");
        tv.getItems().add(i);

        //LinkedList<>
        // STAGE:
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(header);
        secondaryStage.setScene(scene);
        secondaryStage.show();

        // EVENT HANDLERS:
        // add->new stage:
        add.setOnAction(e -> new PhoneNumberStage("New Phone Number"));
//        add.setOnAction(e -> new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//                // need to set phone number and type to new PhoneNumberRecord!
//                PhoneNumberStage pns = new PhoneNumberStage("howdy");
//            }
//        });
        // edit->new stage:
        edit.setOnAction(e -> new PhoneNumberStage("Edit Phone Number"));
        // control:
        cancel.setOnAction(e -> secondaryStage.close());
        // ok->create new RolodexRecord:
        ok.setOnAction(e -> new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                obj.setFirstName(firstField.getText());
                obj.setLastName(lastField.getText());
                obj.setMiddleName(middleField.getText());
            }
        });
        
    }
}

// TERNARY STAGE CLASS:
class PhoneNumberStage
{

    PhoneNumberStage(String header)
    {

        // ROOT NODE & SCENE:
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 200);

        // CHILD NODES:
        // CHILD: 1
        // text:
        Text mainText1 = new Text("Please enter Phone Book data and press OK button.  Press Cancel to discard all changes.");
        VBox tBox = new VBox(mainText1);
        tBox.setPadding(new Insets(0, 0, 0, 0));
        tBox.setSpacing(5);

        // CHILD: 2
        // phone pane:
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);
        centerBox.setPadding(new Insets(10, 0, 10, 0));
        Text numberText = new Text("Phone Number:");
        numberText.setStyle("-fx-font-weight: bold;");
        Text typeText = new Text("Phone Type:");
        typeText.setStyle("-fx-font-weight: bold;");
        TextField numberField = new TextField();
        ComboBox typeBox = new ComboBox();
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Home",
                        "Work",
                        "Personal"
                );
        typeBox.setItems(options);
        typeBox.setPrefWidth(167);

        HBox row1 = new HBox(numberText, numberField);
        HBox row2 = new HBox(typeText, typeBox);
        row1.setSpacing(31);
        row2.setSpacing(50);
        VBox phoneBox = new VBox(row1, row2);
        phoneBox.setSpacing(5);
        phoneBox.setPadding(new Insets(10, 0, 5, 0));
        // line separators:
        Separator s1 = new Separator();
        Separator s2 = new Separator();
        centerBox.getChildren().addAll(s1, phoneBox, s2);
        centerBox.setPadding(new Insets(0, 0, 0, 10));

        // CHILD: 3
        // ok-cancel buttons box:
        Button ok = new Button("OK");
        ok.setPrefWidth(80);
        Button cancel = new Button("Cancel");
        cancel.setPrefWidth(90);
        HBox confirmBox = new HBox(ok, cancel);
        confirmBox.setSpacing(10);
        confirmBox.setPadding(new Insets(0, 0, 0, 0));
        confirmBox.setAlignment(Pos.CENTER_RIGHT);

        // root node formatting:
        root.setTop(tBox);
        root.setCenter(centerBox);
        root.setBottom(confirmBox);
        root.setPadding(new Insets(15, 10, 10, 10));

        // STAGE:
        Stage ternaryStage = new Stage();
        ternaryStage.setTitle(header);
        ternaryStage.setScene(scene);
        ternaryStage.show();

        // EVENTS:
        // confirmation handler:
        ok.setOnAction(e -> new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                // need to set phone number and type to new PhoneNumberRecord!
            }
        });
        cancel.setOnAction(e -> ternaryStage.close());
    }

}
