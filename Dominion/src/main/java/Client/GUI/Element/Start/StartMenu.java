package Client.GUI.Element.Start;

import Client.DominionManager;
import Client.GUI.Element.Misc.TranslucentRectangle;
import static Constant.GuiSettings.StartScreen.*;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartMenu extends BorderPane {

    private Group startMenu;
    private Group hostMenu;
    private Group joinMenu;

    private final double x;
    private final double y;

    public StartMenu(double x, double y) {
        this.x = x;
        this.y = y;

        Rectangle start_bg = new TranslucentRectangle(MENU_RECT_WIDTH, MENU_RECT_HEIGHT, this.x, this.y,
                                                        Color.BLACK, 0.5, 1.5);
        getChildren().add(start_bg);

        createStartMenu();
        createHostMenu();
        createJoinMenu();
        switchToStartMenu();
    }

    private VBox createMenuRegion(String title) {
        VBox menu = new VBox();
        menu.setPadding(new Insets(this.y + 10, this.x, this.y, this.x + 20));
        menu.setSpacing(10);

        Text name = new Text(title);
        name.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        name.setFill(Color.WHITE);

        menu.getChildren().add(name);
        return menu;
    }


    /*
     * START MENU
     */
    private void createStartMenu() {
        startMenu = new Group();

        // Menu Contents
        VBox options = createMenuRegion("Match Options:");

        VBox buttons = new VBox();
        buttons.setSpacing(5);

        StartMenuButton hostButton = new StartMenuButton("Host Game", 260, 30);
        StartMenuButton joinButton = new StartMenuButton("Join Game", 260, 30);

        hostButton.setOnMousePressed( e -> {
            switchToHostMenu();
        });

        joinButton.setOnMousePressed( e -> {
            switchToJoinMenu();
        });

        buttons.getChildren().addAll(hostButton, joinButton);
        options.getChildren().add(buttons);

        // Putting it together
        startMenu.getChildren().add(options);
        getChildren().add(startMenu);
    }

    private void switchToStartMenu() {
        startMenu.toFront();

        startMenu.setVisible(true);
        hostMenu.setVisible(false);
        joinMenu.setVisible(false);
    }

    /*
     * HOST MENU
     */

    private void createHostMenu() {
        hostMenu = new Group();
        hostMenu.setVisible(false);

        // Menu Contents
        VBox options = createMenuRegion("Host Game:");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        final Label lblName = new Label("Username");
        lblName.setTextFill(Color.WHITE);
        grid.add(lblName, 0, 2);

        TextField txtName = new TextField();
        txtName.setPromptText("HostMan123");
        txtName.setFocusTraversable(false);

        txtName.textProperty().addListener((observable, oldName, newName) ->
            DominionManager.getInstance().getConnectionConfig().setUsername(newName)
        );

        grid.add(txtName, 1, 2);

        final Label lblPort = new Label("Port");
        lblPort.setTextFill(Color.WHITE);
        grid.add(lblPort, 0, 3);

        TextField txtPort = new TextField();
        txtPort.setPromptText("1234");
        txtPort.setFocusTraversable(false);

        txtPort.textProperty().addListener((observable, oldPort, newPort) ->
                DominionManager.getInstance().getConnectionConfig().setHostPort(Integer.parseInt(newPort))
        );

        grid.add(txtPort, 1, 3);

        final Label lblNumPlayers = new Label("Lobby Size");
        lblNumPlayers.setTextFill(Color.WHITE);
        grid.add(lblNumPlayers, 0, 4);

        ObservableList<Integer> numPlayers =
                FXCollections.observableArrayList(
                        2,
                        3,
                        4
                );
        final ComboBox<Integer> cmbLobbySize = new ComboBox<>(numPlayers);
        cmbLobbySize.setFocusTraversable(false);
        cmbLobbySize.getSelectionModel().selectFirst();
        DominionManager.getInstance().getConnectionConfig().setLobbySize(cmbLobbySize.getValue());
        cmbLobbySize.valueProperty().addListener((ov, oldSize, newSize) ->
                DominionManager.getInstance().getConnectionConfig().setLobbySize(newSize)
        );

        grid.add(cmbLobbySize, 1, 4);

        Button btnBack = new Button("Go Back");
        btnBack.setMinWidth(80);
        btnBack.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: indianred");
        btnBack.setFocusTraversable(false);

        btnBack.setOnMousePressed( e -> {
            switchToStartMenu();
        });

        HBox buttons = new HBox();
        buttons.setSpacing(5);

        Button btnHost = new Button("Host Game");
        btnHost.setFocusTraversable(false);
        btnHost.setOnAction(e -> {
            txtName.setDisable(true);
            txtPort.setDisable(true);
            cmbLobbySize.setDisable(true);
            btnBack.setDisable(true);
            btnHost.setDisable(true);
            DominionManager.getInstance().hostLobby();
        });

        buttons.getChildren().addAll(btnHost, btnBack);

        grid.add(buttons, 1, 5);

        options.getChildren().add(grid);

        hostMenu.getChildren().add(options);
        getChildren().add(hostMenu);
    }

    private void switchToHostMenu() {
        hostMenu.toFront();

        startMenu.setVisible(false);
        hostMenu.setVisible(true);
        joinMenu.setVisible(false);
    }

    /*
     * JOIN MENU
     */

    private void createJoinMenu() {
        joinMenu = new Group();
        joinMenu.setVisible(false);

        // Menu Contents
        VBox options = createMenuRegion("Join Game:");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Label lblName = new Label("Username");
        lblName.setTextFill(Color.WHITE);
        grid.add(lblName, 0, 2);

        TextField txtName = new TextField();
        txtName.setPromptText("DominionGuy321");
        txtName.setFocusTraversable(false);

        txtName.textProperty().addListener((observable, oldName, newName) -> {
            DominionManager.getInstance().getConnectionConfig().setUsername(newName);
        });

        grid.add(txtName, 1, 2);

        Label lblAddress = new Label("IP Address");
        lblAddress.setTextFill(Color.WHITE);
        grid.add(lblAddress, 0, 3);

        TextField txtAddress = new TextField();
        txtAddress.setPromptText("127.0.0.1");
        txtAddress.setFocusTraversable(false);

        txtAddress.textProperty().addListener((observable, oldAddress, newAddress) ->
                DominionManager.getInstance().getConnectionConfig().setHostName(newAddress)
        );

        grid.add(txtAddress, 1, 3);

        Label lblPort = new Label("Host Port");
        lblPort.setTextFill(Color.WHITE);
        grid.add(lblPort, 0, 4);

        TextField txtPort = new TextField();
        txtPort.setPromptText("1234");
        txtPort.setFocusTraversable(false);

        txtPort.textProperty().addListener((observable, oldPort, newPort) ->
                DominionManager.getInstance().getConnectionConfig().setHostPort(Integer.parseInt(newPort))
        );

        grid.add(txtPort, 1, 4);

        HBox buttons = new HBox();
        buttons.setSpacing(5);

        Button btnBack = new Button("Go Back");
        btnBack.setMinWidth(80);
        btnBack.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: indianred");
        btnBack.setFocusTraversable(false);

        btnBack.setOnMousePressed( e -> {
            switchToStartMenu();
        });

        Button btnJoin = new Button("Join Game");
        btnJoin.setFocusTraversable(false);
        btnJoin.setOnAction(e -> {
            txtName.setDisable(true);
            txtAddress.setDisable(true);
            txtPort.setDisable(true);
            btnBack.setDisable(true);
            btnJoin.setDisable(true);

            DominionManager.getInstance().joinLobby();
        });

        buttons.getChildren().addAll(btnJoin, btnBack);

        grid.add(buttons, 1, 5);

        options.getChildren().add(grid);

        joinMenu.getChildren().add(options);
        getChildren().add(joinMenu);
    }

    private void switchToJoinMenu() {
        joinMenu.toFront();

        startMenu.setVisible(false);
        hostMenu.setVisible(false);
        joinMenu.setVisible(true);
    }
}
