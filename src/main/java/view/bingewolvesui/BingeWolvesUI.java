package view.bingewolvesui;

import java.io.IOException;
import java.net.URL;

import controller.bingewolves.ApiDataRequest;
import controller.bingewolves.ApiDataRequest.Params;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
/**
 * This is a program that will build the UI for the BingeWolves application that will allow users to
 * search for a World of Warcraft Character, a mount, or a pet from the game and be shown information
 * from those searches.
 * 
 * @author David Alvarez, 2/23/19
 * @version 1.0
 */
public class BingeWolvesUI extends Application 
{
    private Scene mainScreen, chrSearchScreen, chrStatScreen, chrCollectionsScreen,
	  mountSearchScreen, mountDataScreen, petSearchScreen, petDataScreen;
    //Buttons for the main screen
    private final Button chrSearchScreenBtn = new Button("Character Search");		
    private final Button mountSearchScreenBtn = new Button("Mount Search");
    private final Button petSearchScreenBtn = new Button("Pet Search");
    //Buttons for the character search screen
    private final Button homeCSBtn = new Button("Home");
    private final Button mountCSBtn = new Button("Mount Search");
    private final Button petCSBtn = new Button("Pet Search");
    //Buttons for the character stat screen
    private final Button homeCDBtn = new Button("Home");
    private final Button mountCDBtn = new Button("Mount Search");
    private final Button petCDBtn = new Button("Pet Search");
    private final Button chrCollectionBtn = new Button("Collections");
    //Buttons for the character collection screen
    private final Button chrStatBtn = new Button("Character Stats");
    //Buttons for the mount search screen
    private final Button homeMSBtn = new Button("Home");
    private final Button chrMSBtn = new Button("Character Search");
    private final Button petMSBtn = new Button("Pet Search");
    //Buttons for the mount data screen
    private final Button homeMDBtn = new Button("Home");
    private final Button chrMDBtn = new Button("Character Search");
    private final Button petMDBtn = new Button("Pet Search");
    //Buttons for the pet search screen
    private final Button homePSBtn = new Button("Home");
    private final Button chrPSBtn = new Button("Character Search");
    private final Button mountPSBtn = new Button("Mount Search");
    //Buttons for the pet data screen
    private final Button homePDBtn = new Button("Home");
    private final Button chrPDBtn = new Button("Character Search");
    private final Button mountPDBtn = new Button("Mount Search");
    //The actual search buttons that will pass the data inputed by the user
    private final Button chrSearchBtn = new Button("Search");
    private final Button mountSearchBtn = new Button("Search");
    private final Button petSearchBtn = new Button("Search"); 
    //Character Search Screen layouts
    private Text chrDNE = new Text("");
    private TextField chrName;
	private TextField realmName;
	private ComboBox<String> regionCB;
	//Character Stat Screen layouts
	Text characterName = new Text();
	static ImageView chrImView = new ImageView();
	static ImageView healthImView = new ImageView();
	private String healthImagePath;
	Text healthStats = new Text();
	static ImageView characterResources = new ImageView();
	private String chrResourcesImagePath;
	Text chrResourceStats = new Text();
	static ImageView primaryStatImView = new ImageView();
	private String primaryStatImagePath;
	Text primaryStat = new Text();
	static ImageView staminaImView = new ImageView();
	private String staminaImagePath;
	Text staminaStat = new Text();
	static ImageView criticalStrikeImView = new ImageView();
	private String critImagePath;
	Text critStat = new Text();
	static ImageView hasteImView = new ImageView();
	private String hasteImagePath;
	Text hasteStat = new Text();
	static ImageView masteryImView = new ImageView();
	private String masteryImagePath;
	Text masteryStat = new Text();
	static ImageView versatilityImView = new ImageView();
	private String versatilityImagePath;
	Text versatilityStat = new Text();
	//Character Collection Screen layouts
	Text totalAchiev = new Text();
	Text totalMounts = new Text();
	Text totalPets = new Text();
	static ImageView petSlot1ImView = new ImageView();
	private String slot1ImagePath;
	Text slot1N = new Text();
	Text slot1Level = new Text();
	static ImageView petSlot2ImView = new ImageView();
	private String slot2ImagePath;
	Text slot2N = new Text();
	Text slot2Level = new Text();
	static ImageView petSlot3ImView = new ImageView();
	private String slot3ImagePath;
	Text slot3N = new Text();
	Text slot3Level = new Text();
	//Mount Search Screen layouts
	private Text mountDNE = new Text("");
    private TextField mountName;
    //Pet Search Screen layouts
    private Text petDNE = new Text("");
    private TextField petName;
    //Mount Data Screen layouts
    Text mountN = new Text();
    static ImageView mountImView = new ImageView();
    public Label mountDescription = new Label();
    public Label mountHowToObtain = new Label();
    //Pet Data Screen layouts
    Text petN = new Text();
    static ImageView petImView = new ImageView();
    public Label petDescription = new Label();
    public Label petSpecies = new Label();
    public Label petHowToObtain = new Label();
    //Image Paths
	private final String logoPath = "/wolf.png";
	private String chrImagePath;
	private String mountImagePath;
	private String petImagePath;
	/**
	 * This method is used to launch the JavaFX UI
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)
	{
		//calling getPetList so that it is loaded into memory and only called once
		ApiDataRequest.getPetList();
		//calling getMountList so that it is loaded into memory and only called once
		try {
			ApiDataRequest.getMountList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainScreen = new Scene(mainScreenLayout(), 1000, 700);
		chrSearchScreen = new Scene(chrSearchScreenLayout(), 1000, 700);
		chrStatScreen = new Scene(chrStatScreenLayout());
		chrCollectionsScreen = new Scene(chrCollectionScreenLayout(), 1000, 700);
		mountSearchScreen = new Scene(mountSearchScreenLayout(), 1000, 700);
		mountDataScreen = new Scene(mountDataScreenLayout(), 1000, 700);
		petSearchScreen = new Scene(petSearchScreenLayout(), 1000, 700);
		petDataScreen = new Scene(petDataScreenLayout(), 1000, 700);

		//Display mainScreen first
		primaryStage.setScene(mainScreen);
		primaryStage.getIcons().add(new Image(logoPath));
		primaryStage.setTitle("BingeWolves");

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() 
		{
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}	
		});
		//Main Screen
		chrSearchScreenBtn.setOnAction(e -> primaryStage.setScene(chrSearchScreen));
		mountSearchScreenBtn.setOnAction(e -> primaryStage.setScene(mountSearchScreen));
		petSearchScreenBtn.setOnAction(e -> primaryStage.setScene(petSearchScreen));
		//Character Search Screen
		homeCSBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		mountCSBtn.setOnAction(e -> primaryStage.setScene(mountSearchScreen));
		petCSBtn.setOnAction(e -> primaryStage.setScene(petSearchScreen));
		//Character Stat Screen
		homeCDBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		mountCDBtn.setOnAction(e -> primaryStage.setScene(mountSearchScreen));
		petCDBtn.setOnAction(e -> primaryStage.setScene(petSearchScreen));
		//Character Collection Screen
		chrStatBtn.setOnAction(e -> primaryStage.setScene(chrStatScreen));
		//Mount Search Screen
		homeMSBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		chrMSBtn.setOnAction(e -> primaryStage.setScene(chrSearchScreen));
		petMSBtn.setOnAction(e -> primaryStage.setScene(petSearchScreen));
		//Mount Data Screen
		homeMDBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		chrMDBtn.setOnAction(e -> primaryStage.setScene(chrSearchScreen));
		petMDBtn.setOnAction(e -> primaryStage.setScene(petSearchScreen));
		//Pet Search Screen
		homePSBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		chrPSBtn.setOnAction(e -> primaryStage.setScene(chrSearchScreen));
		mountPSBtn.setOnAction(e -> primaryStage.setScene(mountSearchScreen));
		//Pet Data Screen
		homePDBtn.setOnAction(e -> primaryStage.setScene(mainScreen));
		chrPDBtn.setOnAction(e -> primaryStage.setScene(chrSearchScreen));
		mountPDBtn.setOnAction(e -> primaryStage.setScene(mountSearchScreen));

		chrSearchBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				ApiDataRequest.selectedSearch = Params.CHARACTER;
				ApiDataRequest.chrRequestBuilder(realmName.getText(), chrName.getText(), regionCB.getValue());
				ApiDataRequest.formatCharacterData(chrDNE);
				if (chrDNE.getText().isEmpty())
				{
					//checking for %s so that I know where to position the character title
					if (ApiDataRequest.title.startsWith("%s"))
					{
						characterName.setText(chrName.getText() + " ," + ApiDataRequest.title.substring(3));
					}
					else if(ApiDataRequest.title.endsWith("%s"))
					{
						characterName.setText(ApiDataRequest.title.substring(0, ApiDataRequest.title.length() - 3) + ", " + chrName.getText());
					}
					else
					{
						characterName.setText(chrName.getText());
					}
					chrName.clear();
					realmName.clear();
					
					chrImagePath = "http://render-" + regionCB.getValue() + ".worldofwarcraft.com/character/" + ApiDataRequest.chrImagePath 
							+ "main.jpg?alt=/wow/static/images/2d/avatar/1-0.jpg";
					Image chrImage = new Image(chrImagePath, 1000, 600, false, false);
					chrImView.setImage(chrImage);
					
					healthImagePath = "/statIcons/health.PNG";
					Image healthImage = new Image(healthImagePath, 46, 46, false, false);
					healthImView.setImage(healthImage);
					healthStats.setText(ApiDataRequest.health + "\nHEALTH");
					//Checking for these numbers so that I know which class that character is and load the correct
					//images for their class
					if (ApiDataRequest.classNum.equals("1"))
					{
						chrResourcesImagePath = "/statIcons/rage.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nRAGE");
					}
					else if (ApiDataRequest.classNum.equals("3"))
					{
						chrResourcesImagePath = "/statIcons/focus.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nFOCUS");
					}
					else if (ApiDataRequest.classNum.equals("6"))
					{
						chrResourcesImagePath = "/statIcons/runicPower.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nRUNIC POWER");
					}
					else if (ApiDataRequest.classNum.equals("4") || ApiDataRequest.classNum.equals("10"))
					{
						chrResourcesImagePath = "/statIcons/energy.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nENERGY");
					}
					else if (ApiDataRequest.classNum.equals("12"))
					{
						chrResourcesImagePath = "/statIcons/fury.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nFURY");
					}
					else if (ApiDataRequest.classNum.equals("7"))
					{
						chrResourcesImagePath = "/statIcons/maelstrom.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nMAELSTROM");
					}
					else
					{
						chrResourcesImagePath = "/statIcons/mana.PNG";
						chrResourceStats.setText(ApiDataRequest.chrResources + "\nMANA");
					}
					Image resourceImage = new Image(chrResourcesImagePath, 46, 46, false, false);
					characterResources.setImage(resourceImage);
					//Checking for the class num so that I know which primray stat image to load
					if (ApiDataRequest.classNum.equals("1") || ApiDataRequest.classNum.equals("2") || ApiDataRequest.classNum.equals("6"))
					{
						primaryStatImagePath = "/statIcons/strength.PNG";
						primaryStat.setText(ApiDataRequest.strength + "\nSTRENGTH");
					}
					else if (ApiDataRequest.classNum.equals("5") || ApiDataRequest.classNum.equals("8") || ApiDataRequest.classNum.equals("9"))
					{
						primaryStatImagePath = "/statIcons/intellect.PNG";
						primaryStat.setText(ApiDataRequest.intellect + "\nINTELLECT");
					}
					else
					{
						primaryStatImagePath = "/statIcons/agility.PNG";
						primaryStat.setText(ApiDataRequest.agility + "\nAGILITY");
					}
					Image primaryStatImage = new Image(primaryStatImagePath, 46, 46, false, false);
					primaryStatImView.setImage(primaryStatImage);
					
					staminaImagePath = "/statIcons/stamina.PNG";
					Image staminaImage = new Image(staminaImagePath, 46, 46, false, false);
					staminaImView.setImage(staminaImage);
					staminaStat.setText(ApiDataRequest.stamina + "\nSTAMINA");
					
					critImagePath = "/statIcons/criticalStrike.PNG";
					Image critImage = new Image(critImagePath, 46, 46, false, false);
					criticalStrikeImView.setImage(critImage);
					critStat.setText(ApiDataRequest.criticalStrike + "%" + "\nCRITICAL\nSTRIKE");
					
					hasteImagePath = "/statIcons/haste.PNG";
					Image hasteImage = new Image(hasteImagePath, 50, 50, false, false);
					hasteImView.setImage(hasteImage);
					hasteStat.setText(ApiDataRequest.haste + "%" + "\nHASTE");
					
					masteryImagePath = "/statIcons/mastery.PNG";
					Image masteryImage = new Image(masteryImagePath, 50, 50, false, false);
					masteryImView.setImage(masteryImage);
					masteryStat.setText(ApiDataRequest.mastery + "%" + "\nMASTERY");
					
					versatilityImagePath = "/statIcons/versatility.PNG";
					Image versatilityImage = new Image(versatilityImagePath, 46, 46, false, false);
					versatilityImView.setImage(versatilityImage);
					versatilityStat.setText(ApiDataRequest.versatility + "\nVERSATILITY");
					
					primaryStage.sizeToScene();
					primaryStage.setScene(chrStatScreen);
				}
			}
		});
		chrCollectionBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				totalAchiev.setText("Total: " + ApiDataRequest.achievPoints);
				totalMounts.setText("Total Collected: " + ApiDataRequest.mountsCollected);
				totalPets.setText("Total Collected: " + ApiDataRequest.petsCollected);
				
				slot1ImagePath = "PetImages/" + ApiDataRequest.petSlot1 + ".jpg";
				Image slot1Image = new Image(slot1ImagePath, 150, 150, false, false);
				petSlot1ImView.setImage(slot1Image);
				slot1N.setText(ApiDataRequest.slot1Name);
				slot1Level.setText("Level: " + ApiDataRequest.slot1L);
				
				slot2ImagePath = "PetImages/" + ApiDataRequest.petSlot2 + ".jpg";
				Image slot2Image = new Image(slot2ImagePath, 150, 150, false, false);
				petSlot2ImView.setImage(slot2Image);
				slot2N.setText(ApiDataRequest.slot2Name);
				slot2Level.setText("Level: " + ApiDataRequest.slot2L);
				
				slot3ImagePath = "PetImages/" + ApiDataRequest.petSlot3 + ".jpg";
				Image slot3Image = new Image(slot3ImagePath, 150, 150, false, false);
				petSlot3ImView.setImage(slot3Image);
				slot3N.setText(ApiDataRequest.slot3Name);
				slot3Level.setText("Level: " + ApiDataRequest.slot3L);
				
				primaryStage.setScene(chrCollectionsScreen);
			}
		});
		mountSearchBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				ApiDataRequest.mountRequestBuilder(mountName.getText(), mountDNE);
				if (mountDNE.getText().isEmpty()) {
					mountName.clear();
					ApiDataRequest.formatMountData(mountDescription, mountHowToObtain);
					mountN.setText(ApiDataRequest.mName);
					mountImagePath = "/mountImages/" + ApiDataRequest.mDisplayId + ".jpg";
					Image mountImage = new Image(mountImagePath, 300, 300, false, false);
					mountImView.setImage(mountImage);
					primaryStage.setScene(mountDataScreen);
				}
			}
		});
		petSearchBtn.setOnAction(new EventHandler<ActionEvent>()
		{	
			public void handle(ActionEvent e){
				ApiDataRequest.selectedSearch = Params.PET;
				ApiDataRequest.petRequestBuilder(petName.getText(), petDNE);
				if (petDNE.getText().isEmpty()) {
					petName.clear();
					ApiDataRequest.formatPetData(petDescription, petSpecies,petHowToObtain);
					petN.setText(ApiDataRequest.pet);
					System.out.println("Hello");
					petImagePath = "PetImages/" + ApiDataRequest.pDisplayId + ".jpg";
					Image petImage = new Image(petImagePath, 300, 300, false, false);
					petImView.setImage(petImage);
					System.out.println("Hello32");
					primaryStage.setScene(petDataScreen);
				}
			}
		});
		primaryStage.show();	
	}
	/**
	 * This method will make the interface of the main screen
	 * 
	 * @return A BorderPane layout of the main screen 
	 */
	private BorderPane mainScreenLayout()
	{
		BorderPane borderMS = new BorderPane(); 
		VBox vboxT = new VBox();
		HBox hboxC = new HBox();
		VBox vboxC = new VBox();
		ImageView imview = new ImageView();

		Image logo = new Image(logoPath, 200, 200, false, false);
		imview.setImage(logo);

		Text title = new Text("Binge Wolves");
		title.setFont(Font.font("Arial", FontPosture.ITALIC, 40));
		vboxT.getChildren().addAll(imview, title);
		vboxT.setAlignment(Pos.CENTER);
		vboxT.setPadding(new Insets(10, 0, 10, 0));

		chrSearchScreenBtn.setPrefSize(150, 50);
		chrSearchScreenBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountSearchScreenBtn.setPrefSize(150, 50);
		mountSearchScreenBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		petSearchScreenBtn.setPrefSize(150, 50);
		petSearchScreenBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));

		vboxC.getChildren().add(petSearchScreenBtn);
		vboxC.setAlignment(Pos.BOTTOM_CENTER);
		vboxC.setPadding(new Insets(0, 0, 125, 0));
		hboxC.getChildren().addAll(chrSearchScreenBtn, vboxC, mountSearchScreenBtn);
		hboxC.setAlignment(Pos.CENTER);
		borderMS.setStyle("-fx-background-color: #808080;");
		borderMS.setTop(vboxT);
		borderMS.setCenter(hboxC);

		return borderMS;
	}
	/**
	 * This method will make the interface of the character search screen
	 * 
	 * @return A BorderPane layout of the character search screen
	 */
	private BorderPane chrSearchScreenLayout() 
	{
		BorderPane borderCS = new BorderPane();
		HBox hboxTL1 = new HBox();
		HBox hboxTL2 = new HBox();
		HBox hboxB = new HBox();
		VBox vboxT = new VBox();
		StackPane pane = new StackPane();
		GridPane grid = new GridPane();

		Text title = new Text("Character Search");
		title.setFont(Font.font("SansSerif", FontWeight.BOLD, 38));

		Polygon trapezoidT =  new Polygon();
		trapezoidT.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidT.setFill(Color.AQUA);
		trapezoidT.setStroke(Color.BLACK);
		trapezoidT.setStrokeWidth(2);

		homeCSBtn.setPrefSize(125, 40);
		homeCSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountCSBtn.setPrefSize(125, 40);
		mountCSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		petCSBtn.setPrefSize(125, 40);
		petCSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		chrDNE.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrDNE.setFill(Color.DARKRED);
		
		Label lblName = new Label("Character Name");
		lblName.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		this.chrName = new TextField();
		chrName.setPromptText("Enter Name Of Character");
		chrName.setPrefSize(200, 30);

		Label lblRealm = new Label("Realm");
		lblRealm.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		this.realmName = new TextField();
		this.realmName.setPromptText("Enter Realm of Character");
		this.realmName.setPrefSize(200, 30);

		Label lblRegion = new Label("Region");
		lblRegion.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		ObservableList<String> options = FXCollections.observableArrayList(
						"us",
						"eu",
						"kr",
						"tw",
						"cn"
		);
		this.regionCB = new ComboBox<>(options);
		this.regionCB.setPrefSize(220, 30);
		this.regionCB.setPromptText("Choose your charcter's region");

		chrSearchBtn.setPrefSize(150, 50);
		chrSearchBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));

		Polygon trapezoidB =  new Polygon();
		trapezoidB.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidB.setRotate(180);
		trapezoidB.setFill(Color.AQUA);
		trapezoidB.setStroke(Color.BLACK);
		trapezoidB.setStrokeWidth(2);

		pane.getChildren().add(trapezoidT);
		pane.getChildren().add(title);
		pane.setAlignment(Pos.CENTER);

		hboxTL1.getChildren().add(pane);
		hboxTL1.setAlignment(Pos.CENTER);
		hboxTL1.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		hboxTL2.getChildren().addAll(homeCSBtn, mountCSBtn, petCSBtn);
		hboxTL2.setAlignment(Pos.CENTER);
		hboxTL2.setSpacing(100);
		hboxTL2.setPadding(new Insets(10, 0, 10, 0));
		hboxTL2.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		vboxT.getChildren().addAll(hboxTL1, hboxTL2);
		vboxT.setAlignment(Pos.CENTER);

		grid.setHgap(20);
		grid.setVgap(10);
		grid.add(chrName, 0, 1);
		grid.add(lblName, 0, 2);
		GridPane.setHalignment(lblName, HPos.CENTER);
		grid.add(chrDNE, 0, 0, 3, 1);
		grid.add(realmName, 1, 1);
		grid.add(lblRealm, 1, 2);
		GridPane.setHalignment(lblRealm, HPos.CENTER);
		grid.add(regionCB, 2, 1);
		grid.add(lblRegion, 2, 2);
		GridPane.setHalignment(lblRegion, HPos.CENTER);
		grid.add(chrSearchBtn, 1, 6);
		GridPane.setHalignment(chrSearchBtn, HPos.CENTER);
		grid.setAlignment(Pos.CENTER);

		hboxB.getChildren().add(trapezoidB);
		hboxB.setAlignment(Pos.CENTER);
		hboxB.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		borderCS.setTop(vboxT);
		borderCS.setCenter(grid);
		borderCS.setBottom(hboxB);
		borderCS.setStyle("-fx-background-color: #808080;");

		return borderCS;
	}
	/**
	 * This method will make the interface of the character stat screen which will
	 * layout the data returned from the character search.
	 * 
	 * @return A BorderPane layout of the character stat screen 
	 */
	private BorderPane chrStatScreenLayout()
	{
		BorderPane borderCSS = new BorderPane();
		HBox hboxL1 = new HBox();
		VBox vboxL1 = new VBox();
		GridPane grid = new GridPane();
		
		homeCDBtn.setPrefSize(125, 40);
		homeCDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountCDBtn.setPrefSize(125, 40);
		mountCDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		petCDBtn.setPrefSize(125, 40);
		petCDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrCollectionBtn.setPrefSize(125, 40);
		chrCollectionBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		characterName.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		characterName.setFill(Color.rgb(198, 155, 109));
		
		healthStats.setFont(Font.font("SansSerif", 12));
		healthStats.setFill(Color.WHITE);
		
		chrResourceStats.setFont(Font.font("SansSerif", 12));
		chrResourceStats.setFill(Color.WHITE);
		
		primaryStat.setFont(Font.font("SansSerif", 12));
		primaryStat.setFill(Color.WHITE);
		
		staminaStat.setFont(Font.font("SansSerif", 12));
		staminaStat.setFill(Color.WHITE);
		
		critStat.setFont(Font.font("SansSerif", 12));
		critStat.setFill(Color.WHITE);
		
		hasteStat.setFont(Font.font("SansSerif", 12));
		hasteStat.setFill(Color.WHITE);
		
		masteryStat.setFont(Font.font("SansSerif", 12));
		masteryStat.setFill(Color.WHITE);
		
		versatilityStat.setFont(Font.font("SansSerif", 12));
		versatilityStat.setFill(Color.WHITE);
		
		hboxL1.getChildren().addAll(homeCDBtn, mountCDBtn, petCDBtn, chrCollectionBtn);
		hboxL1.setAlignment(Pos.CENTER);
		hboxL1.setSpacing(100);
		hboxL1.setPadding(new Insets(10, 0, 10, 0));
		hboxL1.setStyle("-fx-background-color: #201410;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");
		
		vboxL1.getChildren().addAll(characterName, chrImView, grid);
		vboxL1.setAlignment(Pos.CENTER);
		vboxL1.setSpacing(20);
		
		grid.setHgap(5);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.add(healthImView, 0, 0);
		grid.add(healthStats, 1, 0);
		grid.add(characterResources, 2, 0);
		grid.add(chrResourceStats, 3, 0);
		grid.add(primaryStatImView, 4, 0);
		grid.add(primaryStat, 5, 0);
		grid.add(staminaImView, 6, 0);
		grid.add(staminaStat, 7, 0);
		grid.add(criticalStrikeImView, 0, 1);
		grid.add(critStat, 1, 1);
		grid.add(hasteImView, 2, 1);
		grid.add(hasteStat, 3, 1);
		grid.add(masteryImView, 4, 1);
		grid.add(masteryStat, 5, 1);
		grid.add(versatilityImView, 6, 1);
		grid.add(versatilityStat, 7, 1);
		
		borderCSS.setTop(hboxL1);
		borderCSS.setCenter(vboxL1);
		borderCSS.setStyle("-fx-background-color: #1a0407;");
		
		return borderCSS;
	}
	/**
	 * This method will make the interface of the character collection screen
	 * which will layout out data returned from the character search.
	 * 
	 * @return A BorderPane layout of the character collection screen
	 */
	private BorderPane chrCollectionScreenLayout()
	{
		BorderPane borderCCS = new BorderPane();
		HBox hboxL1 = new HBox();
		VBox vbox = new VBox();
		GridPane grid = new GridPane();
		
		chrStatBtn.setPrefSize(125, 40);
		chrStatBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		Text achiev = new Text("Achievement Points");
		achiev.setFont(Font.font("Arial", 30));
		achiev.setFill(Color.YELLOW);
		totalAchiev.setFont(Font.font("SansSerif", 16));
		totalAchiev.setFill(Color.WHITE);
		
		Text mountTitle = new Text("Mounts");
		mountTitle.setFont(Font.font("Arial", 30));
		mountTitle.setFill(Color.YELLOW);
		totalMounts.setFont(Font.font("SansSerif", 16));
		totalMounts.setFill(Color.WHITE);
		
		Text petTitle = new Text("Pets");
		petTitle.setFont(Font.font("Arial", 30));
		petTitle.setFill(Color.YELLOW);
		totalPets.setFont(Font.font("SansSerif", 16));
		totalPets.setFill(Color.WHITE);
		
		Text petSlotsTitle = new Text("Battle Pet Slots");
		petSlotsTitle.setFont(Font.font("Arial", 30));
		petSlotsTitle.setFill(Color.YELLOW);
		Text slot1 = new Text("Slot 1");
		slot1.setFont(Font.font("SansSerif", 16));
		slot1.setFill(Color.WHITE);
		slot1N.setFont(Font.font("SansSerif", 16));
		slot1N.setFill(Color.WHITE);
		slot1Level.setFont(Font.font("SansSerif", 16));
		slot1Level.setFill(Color.WHITE);
		Text slot2 = new Text("Slot 2");
		slot2.setFont(Font.font("SansSerif", 16));
		slot2.setFill(Color.WHITE);
		slot2N.setFont(Font.font("SansSerif", 16));
		slot2N.setFill(Color.WHITE);
		slot2Level.setFont(Font.font("SansSerif", 16));
		slot2Level.setFill(Color.WHITE);
		Text slot3 = new Text("Slot 3");
		slot3.setFont(Font.font("SansSerif", 16));
		slot3.setFill(Color.WHITE);
		slot3N.setFont(Font.font("SansSerif", 16));
		slot3N.setFill(Color.WHITE);
		slot3Level.setFont(Font.font("SansSerif", 16));
		slot3Level.setFill(Color.WHITE);
		
		DropShadow ds = new DropShadow( 20, Color.AQUA );
		petSlot1ImView.setEffect(ds);
		petSlot2ImView.setEffect(ds);
		petSlot3ImView.setEffect(ds);
		
		hboxL1.getChildren().add(chrStatBtn);
		hboxL1.setAlignment(Pos.CENTER);
		hboxL1.setSpacing(100);
		hboxL1.setPadding(new Insets(10, 0, 10, 0));
		hboxL1.setStyle("-fx-background-color: #201410;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(70);
		grid.setVgap(15);
		grid.add(petSlot1ImView, 0, 0);
		grid.add(slot1, 0, 1);
		GridPane.setHalignment(slot1, HPos.CENTER);
		grid.add(slot1N, 0, 2);
		GridPane.setHalignment(slot1N, HPos.CENTER);
		grid.add(slot1Level, 0, 3);
		GridPane.setHalignment(slot1Level, HPos.CENTER);
		grid.add(petSlot2ImView, 1, 0);
		grid.add(slot2, 1, 1);
		GridPane.setHalignment(slot2, HPos.CENTER);
		grid.add(slot2N, 1, 2);
		GridPane.setHalignment(slot2N, HPos.CENTER);
		grid.add(slot2Level, 1, 3);
		GridPane.setHalignment(slot2Level, HPos.CENTER);
		grid.add(petSlot3ImView, 2, 0);
		grid.add(slot3, 2, 1);
		GridPane.setHalignment(slot3, HPos.CENTER);
		grid.add(slot3N, 2, 2);
		GridPane.setHalignment(slot3N, HPos.CENTER);
		grid.add(slot3Level, 2, 3);
		GridPane.setHalignment(slot3Level, HPos.CENTER);
		
		vbox.getChildren().addAll(achiev, totalAchiev, mountTitle, totalMounts, petTitle, totalPets, petSlotsTitle, grid);
		vbox.setPadding(new Insets(20, 0, 20, 20));
		vbox.setSpacing(20);
		
		borderCCS.setTop(hboxL1);
		borderCCS.setCenter(vbox);
		borderCCS.setStyle("-fx-background-color: #1a0407;");
		
		return borderCCS;
	}
	/**
	 * This method will make the interface of the mount search screen
	 * 
	 * @return A BorderPane layout of the mount search screen
	 */
	private BorderPane mountSearchScreenLayout() 
	{
		BorderPane borderMS = new BorderPane();
		HBox hboxTL1 = new HBox();
		HBox hboxTL2 = new HBox();
		HBox hboxB = new HBox();
		VBox vboxT = new VBox();
		StackPane pane = new StackPane();
		GridPane grid = new GridPane();

		Text title = new Text("Mount Search");
		title.setFont(Font.font("SansSerif", FontWeight.BOLD, 38));

		Polygon trapezoidT =  new Polygon();
		trapezoidT.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidT.setFill(Color.AQUA);
		trapezoidT.setStroke(Color.BLACK);
		trapezoidT.setStrokeWidth(2);

		homeMSBtn.setPrefSize(125, 40);
		homeMSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrMSBtn.setPrefSize(125, 40);
		chrMSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		petMSBtn.setPrefSize(125, 40);
		petMSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		mountDNE.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountDNE.setFill(Color.DARKRED);
		Label lblMName = new Label("Mount Name:");
		lblMName.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		this.mountName = new TextField();
		mountName.setPromptText("Enter name of a mount");
		mountName.setPrefSize(200, 30);

		mountSearchBtn.setPrefSize(150, 50);
		mountSearchBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));

		Polygon trapezoidB =  new Polygon();
		trapezoidB.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidB.setRotate(180);
		trapezoidB.setFill(Color.AQUA);
		trapezoidB.setStroke(Color.BLACK);
		trapezoidB.setStrokeWidth(2);

		pane.getChildren().add(trapezoidT);
		pane.getChildren().add(title);
		pane.setAlignment(Pos.CENTER);

		hboxTL1.getChildren().add(pane);
		hboxTL1.setAlignment(Pos.CENTER);
		hboxTL1.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		hboxTL2.getChildren().addAll(homeMSBtn, chrMSBtn, petMSBtn);
		hboxTL2.setAlignment(Pos.CENTER);
		hboxTL2.setSpacing(100);
		hboxTL2.setPadding(new Insets(10, 0, 10, 0));
		hboxTL2.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		vboxT.getChildren().addAll(hboxTL1, hboxTL2);
		vboxT.setAlignment(Pos.CENTER);

		hboxB.getChildren().add(trapezoidB);
		hboxB.setAlignment(Pos.CENTER);
		hboxB.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		grid.setHgap(20);
		grid.setVgap(10);
		grid.add(mountDNE, 1, 0);
		grid.add(lblMName, 0, 1);
		grid.add(mountName, 1, 1);
		grid.add(mountSearchBtn, 1, 4);
		grid.setAlignment(Pos.CENTER);

		borderMS.setTop(vboxT);
		borderMS.setCenter(grid);
		borderMS.setBottom(hboxB);
		borderMS.setStyle("-fx-background-color: #808080;");

		return borderMS;
	}
	/**
	 * This method will make the interface of the mount data screen which will
	 * layout the data returned from the mount search.
	 * 
	 * @return A BorderPane layout of the mount data screen
	 */
	private BorderPane mountDataScreenLayout()
	{
		BorderPane borderMDS = new BorderPane();
		VBox vboxL1 = new VBox();
		HBox hboxL1 = new HBox();
		VBox vboxL2 = new VBox();
		
		homeMDBtn.setPrefSize(125, 40);
		homeMDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrMDBtn.setPrefSize(125, 40);
		chrMDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		petMDBtn.setPrefSize(125, 40);
		petMDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		mountN.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		Text descripTitle = new Text("Description");
		descripTitle.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		this.mountDescription.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
		mountDescription.setMaxWidth(500);
		mountDescription.setAlignment(Pos.CENTER_LEFT);
		mountDescription.setWrapText(true);
		
		Text howToTitle = new Text("How To Obtain");
		howToTitle.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		this.mountHowToObtain.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
		mountHowToObtain.setMaxWidth(500);
		mountHowToObtain.setAlignment(Pos.CENTER_LEFT);
		mountHowToObtain.setWrapText(true);
		
		DropShadow ds = new DropShadow( 20, Color.AQUA );
		mountImView.setEffect(ds);
		
		hboxL1.getChildren().addAll(homeMDBtn, chrMDBtn, petMDBtn);
		hboxL1.setAlignment(Pos.CENTER);
		hboxL1.setSpacing(100);
		hboxL1.setPadding(new Insets(10, 0, 10, 0));
		hboxL1.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");
		
		vboxL1.getChildren().addAll(mountN, mountImView, descripTitle, mountDescription);
		vboxL1.setAlignment(Pos.CENTER);
		vboxL1.setSpacing(20);
		
		vboxL2.getChildren().addAll(howToTitle, mountHowToObtain);
		vboxL2.setAlignment(Pos.CENTER);
		vboxL2.setPadding(new Insets(0, 0, 40, 0));
		vboxL2.setSpacing(30);
		
		borderMDS.setTop(hboxL1);
		borderMDS.setCenter(vboxL1);
		borderMDS.setBottom(vboxL2);
		borderMDS.setStyle("-fx-background-color: #808080;");
		
		return borderMDS;
	}
	/**
	 * This method will make the interface of the pet search screen
	 * 
	 * @return A BorderPane layout of the pet search screen
	 */
	private BorderPane petSearchScreenLayout() 
	{
		BorderPane borderPS = new BorderPane();
		HBox hboxTL1 = new HBox();
		HBox hboxTL2 = new HBox();
		HBox hboxB = new HBox();
		VBox vboxT = new VBox();
		StackPane pane = new StackPane();
		GridPane grid = new GridPane();

		Text title = new Text("Pet Search");
		title.setFont(Font.font("SansSerif", FontWeight.BOLD, 38));

		Polygon trapezoidT =  new Polygon();
		trapezoidT.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidT.setFill(Color.AQUA);
		trapezoidT.setStroke(Color.BLACK);
		trapezoidT.setStrokeWidth(2);

		homePSBtn.setPrefSize(125, 40);
		homePSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrPSBtn.setPrefSize(125, 40);
		chrPSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountPSBtn.setPrefSize(125, 40);
		mountPSBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));

		this.petDNE.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		this.petDNE.setFill(Color.DARKRED);
		Label lblPName = new Label("Pet Name:");
		lblPName.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		this.petName = new TextField();
		petName.setPromptText("Enter name of a pet");
		petName.setPrefSize(200, 30);

		petSearchBtn.setPrefSize(150, 50);
		petSearchBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));

		Polygon trapezoidB =  new Polygon();
		trapezoidB.getPoints().addAll(new Double[] {
				50.0, 50.0,
				550.0, 50.0,
				425.0, 115.0,
				175.0, 115.0
		});
		trapezoidB.setRotate(180);
		trapezoidB.setFill(Color.AQUA);
		trapezoidB.setStroke(Color.BLACK);
		trapezoidB.setStrokeWidth(2);

		pane.getChildren().add(trapezoidT);
		pane.getChildren().add(title);
		pane.setAlignment(Pos.CENTER);

		hboxTL1.getChildren().add(pane);
		hboxTL1.setAlignment(Pos.CENTER);
		hboxTL1.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		hboxTL2.getChildren().addAll(homePSBtn, chrPSBtn, mountPSBtn);
		hboxTL2.setAlignment(Pos.CENTER);
		hboxTL2.setSpacing(100);
		hboxTL2.setPadding(new Insets(10, 0, 10, 0));
		hboxTL2.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		vboxT.getChildren().addAll(hboxTL1, hboxTL2);
		vboxT.setAlignment(Pos.CENTER);

		hboxB.getChildren().add(trapezoidB);
		hboxB.setAlignment(Pos.CENTER);
		hboxB.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");

		grid.setHgap(20);
		grid.setVgap(10);
		grid.add(petDNE, 1, 0);
		grid.add(lblPName, 0, 1);
		grid.add(petName, 1, 1);
		grid.add(petSearchBtn, 1, 4);
		grid.setAlignment(Pos.CENTER);

		borderPS.setTop(vboxT);
		borderPS.setCenter(grid);
		borderPS.setBottom(hboxB);
		borderPS.setStyle("-fx-background-color: #808080;");

		return borderPS;
	}
	/**
	 * This method will make the interface of the pet data screen which will
	 * layout the data returned from the pet search.
	 * 
	 * @return A BorderPane layout of the pet data screen
	 */
	private BorderPane petDataScreenLayout()
	{
		BorderPane borderPDS = new BorderPane();
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		VBox vboxL1 = new VBox();
		HBox hboxL1 = new HBox();
		VBox vboxL2 = new VBox();
		VBox vboxL3 = new VBox();
		
		homePDBtn.setPrefSize(125, 40);
		homePDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		chrPDBtn.setPrefSize(125, 40);
		chrPDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		mountPDBtn.setPrefSize(125, 40);
		mountPDBtn.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
		
		petN.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		Text descripTitle = new Text("Description");
		descripTitle.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		this.petDescription.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
		petDescription.setMaxWidth(500);
		petDescription.setAlignment(Pos.CENTER_LEFT);
		petDescription.setWrapText(true);
		
		Text speciesTitle = new Text("Species");
		speciesTitle.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		this.petSpecies.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
		petSpecies.setAlignment(Pos.CENTER_LEFT);
		petSpecies.setWrapText(true);
		
		Text howToTitle = new Text("How To Obtain");
		howToTitle.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
		this.petHowToObtain.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
		petHowToObtain.setMaxWidth(500);
		petHowToObtain.setAlignment(Pos.CENTER_LEFT);
		petHowToObtain.setWrapText(true);
		
		DropShadow ds = new DropShadow( 20, Color.AQUA );
		petImView.setEffect(ds);
		
		hboxL1.getChildren().addAll(homePDBtn, chrPDBtn, mountPDBtn);
		hboxL1.setAlignment(Pos.CENTER);
		hboxL1.setSpacing(100);
		hboxL1.setPadding(new Insets(10, 0, 10, 0));
		hboxL1.setStyle("-fx-background-color: #859296;" + "-fx-border-style: solid outside;"
				+ "-fx-border-width: 2;" + "-fx-border-color: black;");
		vbox.getChildren().addAll(petN, petImView, hbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		
		vboxL1.getChildren().addAll(descripTitle, petDescription);
		vboxL1.setAlignment(Pos.CENTER_LEFT);
		vboxL1.setSpacing(20);
		
		vboxL3.getChildren().addAll(speciesTitle, petSpecies);
		vboxL3.setAlignment(Pos.BASELINE_LEFT);
		vboxL3.setSpacing(20);
		
		vboxL2.getChildren().addAll(howToTitle, petHowToObtain);
		vboxL2.setAlignment(Pos.CENTER);
		vboxL2.setPadding(new Insets(0, 0, 40, 0));
		vboxL2.setSpacing(30);
		
		hbox.getChildren().addAll(vboxL1, vboxL3);
		hbox.setAlignment(Pos.CENTER);
		
		borderPDS.setTop(hboxL1);
		borderPDS.setCenter(vbox);
		borderPDS.setBottom(vboxL2);
		borderPDS.setStyle("-fx-background-color: #808080;");
		
		return borderPDS;
	}
}
