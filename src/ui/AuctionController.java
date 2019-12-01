package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import DAO.AuctionDAO;
import DAO.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Auction;
import model.Product;
import utils.Utils;

public class AuctionController {

	// Create Auction Tab

	@FXML
	private TextField createLowestPriceTF;

	@FXML
	private DatePicker createExpirationDate;

	@FXML
	private TextField createProductNameTF;

	@FXML
	private Slider sliderHour;

	@FXML
	private Label sliderHourLabel;

	@FXML
	private Slider sliderMinute;

	@FXML
	private Label sliderMinuteLabel;


    @FXML
    private Text createAuctionInfoText;
	


	// My Auctions Tab

	@FXML
	private TableView myAuctionTable;

	@FXML
	private VBox myAuctionTableHolder;
	
	@FXML
	private Text auctionInfoText;
	// Bid Tab

	@FXML
	private Text infoText;

	@FXML
	private TextField bidTextField;

	@FXML
	private Button bidBtn;

	@FXML
	private VBox bidTableHolder;

	@FXML
	private TableView myBidTable;

	@FXML
	public void initialize() {
		initSlider();
		initAuctionsTable();
		initBidTable();

		Utils.addNumberFilter(bidTextField);
		Utils.addNumberFilter(createLowestPriceTF);
	}

	public void initSlider() {
		sliderHour.valueProperty().addListener((observable, oldValue, newValue) -> {
			sliderHourLabel.setText(String.format("%.0f", newValue));
		});

		sliderMinute.valueProperty().addListener((observable, oldValue, newValue) -> {
			sliderMinuteLabel.setText(String.format("%.0f", newValue));
		});

		sliderHourLabel.setText(String.format("%.0f", sliderHour.getValue()));
		sliderMinuteLabel.setText(String.format("%.0f", sliderMinute.getValue()));
	}

	public void initAuctionsTable() {

		this.myAuctionTable = new TableView<>();
		TableColumn idColumn = new TableColumn("Id");
		TableColumn userFromCol = new TableColumn("From");
		TableColumn productCol = new TableColumn("Product");
		TableColumn currentBidCol = new TableColumn("Current Bid");
		TableColumn highestBidderCol = new TableColumn("Highest Bidder");
		TableColumn expDateCol = new TableColumn("Exp. Date");

		if (myAuctionTableHolder.getChildren() != null) {
			myAuctionTableHolder.getChildren().clear();
		}
		myAuctionTableHolder.getChildren().add(myAuctionTable);

		idColumn.setCellValueFactory(new PropertyValueFactory<Auction, Integer>("idAuction"));
		userFromCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("userFromName"));
		productCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("productName"));
		expDateCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("expDateString"));
		currentBidCol.setCellValueFactory(new PropertyValueFactory<Auction, Double>("currentBid"));
		highestBidderCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("highestBidderName"));

		myAuctionTable.getColumns().addAll(idColumn, userFromCol, productCol, currentBidCol, expDateCol,
				highestBidderCol);
		AuctionDAO auctionDAO = new AuctionDAO();
		int currentUserId = Session.getCurrentUser().getIdUser();
		ObservableList<Auction> myAuctions = FXCollections.observableArrayList(auctionDAO.getMyAuctions(currentUserId));
		myAuctionTable.setItems(myAuctions);

	}

	public void initBidTable() {

		this.myBidTable = new TableView<>();
		TableColumn idColumn = new TableColumn("Id");
		TableColumn userFromCol = new TableColumn("From");
		TableColumn productCol = new TableColumn("Product");
		TableColumn currentBidCol = new TableColumn("Current Bid");
		TableColumn highestBidderCol = new TableColumn("Highest Bidder");
		TableColumn expDateCol = new TableColumn("Exp. Date");

		if (bidTableHolder.getChildren() != null) {
			bidTableHolder.getChildren().clear();
		}
		bidTableHolder.getChildren().add(myBidTable);

		idColumn.setCellValueFactory(new PropertyValueFactory<Auction, Integer>("idAuction"));
		userFromCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("userFromName"));
		productCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("productName"));
		expDateCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("expDateString"));
		currentBidCol.setCellValueFactory(new PropertyValueFactory<Auction, Double>("currentBid"));
		highestBidderCol.setCellValueFactory(new PropertyValueFactory<Auction, String>("highestBidderName"));

		myBidTable.getColumns().addAll(idColumn, userFromCol, productCol, currentBidCol, expDateCol, highestBidderCol);
		AuctionDAO auctionDAO = new AuctionDAO();
		int currentUserId = Session.getCurrentUser().getIdUser();
		ObservableList<Auction> allAuctions = FXCollections.observableArrayList(auctionDAO.getAllAuctions());
		myBidTable.setItems(allAuctions);
	}

	@FXML
	void onCreateAuction(ActionEvent event) {
		String lowestPrice = createLowestPriceTF.getText();
		String productName = createProductNameTF.getText();
		LocalDate expDate = createExpirationDate.getValue();
		LocalDateTime expDateTime = expDate.atTime((int) sliderHour.getValue(), (int) sliderMinute.getValue());

		if (!Utils.isFieldValid(productName)) {
			createAuctionInfoText.setText("Invalid product name");
			return;
		}

		double price = Double.parseDouble(lowestPrice);

		if (expDateTime.isBefore(LocalDateTime.now())) {
			auctionInfoText.setText("Expiration date must be in the future");
			return;
		}

		Product product = new Product();
		product.setProductName(productName);
		product.setInitSum(price);

		new ProductDAO().add(product);

		Auction auction = new Auction();
		auction.setExpDate(Utils.asDate(expDateTime));
		auction.setCurrentBid(price);
		auction.setUser1(Session.getCurrentUser());
		auction.setProduct(product);

		new AuctionDAO().add(auction);

		auctionInfoText.setText("Auction added");
	}

	@FXML
	void onBid(ActionEvent event) {
		
		System.out.println("onBid");
		Double bid = Double.parseDouble(bidTextField.getText());

		Auction auction = (Auction) myBidTable.getSelectionModel().getSelectedItem();

		if (auction == null) {
			infoText.setText("Please select auction first");
			return;
		}

		AuctionDAO auctionDAO = new AuctionDAO();
		if (auctionDAO.isAuctionExpired(auction.getIdAuction())) {
			infoText.setText("Auction expired! Refreshing table...");
			initBidTable();
			return;
		}
		
		if (bid <= auction.getCurrentBid()) {
			infoText.setText("Bid value too low");
			System.out.println(bid + " vs " + auction.getCurrentBid());
			return;
		}
		
		auction.setUser2(Session.getCurrentUser());
		auctionDAO.updateBid(auction, bid);
		initBidTable();
		initAuctionsTable();
		
	}

	@FXML
	void onRefreshBids(ActionEvent event) {

		initBidTable();

	}

	@FXML
	void onRefreshAuctions(ActionEvent event) {

		initAuctionsTable();

	}
}
