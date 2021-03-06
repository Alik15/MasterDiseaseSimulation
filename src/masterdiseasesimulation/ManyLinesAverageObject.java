package masterdiseasesimulation;

import datacontainers.DayStat;
import datacontainers.InfoStorage;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import moremethods.MoreMethods;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
/*
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ManyLinesAverageObject {
	public static int daysLimit = 0; //Never go above 10000 days
	public static int maxDays = 100; //Default

//	public static void run() throws IOException {
//		//.config
//		List<String> config = new ArrayList<String>();
//		//List<String> config = readFile(".manyLinesAverageConfig", StandardCharsets.UTF_8); // COMMENTED OUT FOR JAVA VERSION
//		//System.out.println(config);
//
//		ArrayList<String> params = new ArrayList<String>();
//
//		MoreMethods methods = new MoreMethods();
//
//		// Initialize variables
//		int numPeople = 0;
//		int hubNumber = -1;
//		int minFriends = -1;
//		int maxFriends = -1;
//		String layoutString = "";
//		String networkSelectString = "";
//		//String graphString = "";
//		boolean drawJung = true;
//		boolean doFwF = true;
//
//		boolean done = false; // Controls when while loop stops
//
//		// Gets input from user before graph
//		while (!done) {
//			/*
//			//Get params from .config
//			int cNumPeople = getValue(config.get(0));
//			int cMinFriends = getValue(config.get(1));
//			int cMaxFriends = getValue(config.get(2));
//			int cHubNumber = getValue(config.get(3));
//			String cNetwork = getStringValue(config.get(4));
//			boolean cDrawJung = getBoolValue(config.get(5));
//			boolean cDoFwf = getBoolValue(config.get(6));
//			String cLayout = getStringValue(config.get(7));
//			*/ // COMMENTED OUT FOR JAVA VERSION
//
//			JPanel panel = new JPanel();
//			panel.setLayout(new GridLayout(10, 0));
//			JTextField numPeopleAnswer = new JTextField("1000", 10);
//			JTextField minFriendsAnswer = new JTextField("3", 10);
//			JTextField hubNumberAnswer = new JTextField("0", 10);
//			JTextField maxFriendsAnswer = new JTextField("10", 10);
//			String[] possibilities = {"Circle", "FR", "ISOM", "Spring"};
//			JComboBox layoutAnswer = new JComboBox(possibilities);
//			layoutAnswer.setSelectedItem("Circle");
//			String[] possibilitiesNetwork = {"Small World", "Random", "Scale-Free"};
//			JComboBox comboBoxNetwork = new JComboBox(possibilitiesNetwork);
//			comboBoxNetwork.setSelectedItem("Scale-Free");
//			JCheckBox checkBoxGraph = new JCheckBox();
//			checkBoxGraph.setSelected(false);
//			JCheckBox checkBoxFwF = new JCheckBox();
//			checkBoxFwF.setSelected(false);
//
//			panel.add(new JLabel("PEOPLE SETUP:"));
//			panel.add(new JLabel("----------------------------------------------"));
//			panel.add(new JLabel("How many people?"));
//			panel.add(numPeopleAnswer);
//			panel.add(new JLabel("What is the minimum amount of friends?"));
//			panel.add(minFriendsAnswer);
//			panel.add(new JLabel("What is the maximum amount of friends?"));
//			panel.add(maxFriendsAnswer);
//			panel.add(new JLabel("How many hubs (random) or crossover lines (small world)?   "));
//			panel.add(hubNumberAnswer);
//			panel.add(new JLabel("Which type of network?"));
//			panel.add(comboBoxNetwork);
//			panel.add(new JLabel("GRAPH SETUP:"));
//			panel.add(new JLabel("----------------------------------------------"));
//			panel.add(new JLabel("Draw the jung diagram?"));
//			panel.add(checkBoxGraph);
//			panel.add(new JLabel("Perform Friends w/ Friends analysis?"));
//			panel.add(checkBoxFwF);
//			panel.add(new JLabel("Which layout?"));
//			panel.add(layoutAnswer);
//
//			int result = JOptionPane.showConfirmDialog(null, panel, "Before-Graph Configuration", JOptionPane.OK_CANCEL_OPTION);
//
//			if (result != JOptionPane.OK_OPTION) {
//				System.exit(0);
//			}
//
//			try {
//				String numPeopleAnswerString = numPeopleAnswer.getText();
//				numPeople = Integer.parseInt(numPeopleAnswerString);
//				if (numPeople < 1) {
//					throw new NumberFormatException();
//				}
//
//				String hubNumberString = hubNumberAnswer.getText();
//				hubNumber = Integer.parseInt(hubNumberString);
//				if (hubNumber < 0 || hubNumber > numPeople) {
//					throw new NumberFormatException();
//				}
//
//				String minFriendsAnswerString = minFriendsAnswer.getText();
//				minFriends = Integer.parseInt(minFriendsAnswerString);
//				if (minFriends < 0 || minFriends > numPeople) {
//					throw new NumberFormatException();
//				}
//
//				String maxFriendsAnswerString = maxFriendsAnswer.getText();
//				maxFriends = Integer.parseInt(maxFriendsAnswerString);
//				if (minFriends > maxFriends || maxFriends > numPeople) {
//					throw new NumberFormatException();
//				}
//
//				layoutString = (String) layoutAnswer.getSelectedItem();
//				networkSelectString = (String) comboBoxNetwork.getSelectedItem();
//				if (!checkBoxGraph.isSelected()) {
//					drawJung = false;
//				}
//				if (!checkBoxFwF.isSelected()) {
//					doFwF = false;
//				}
//
//				done = true; // Only done when go through try without errors
//			} catch (NumberFormatException e) {
//				if (result == JOptionPane.OK_OPTION) {
//					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Input is invalid.", "Input Error", JOptionPane.ERROR_MESSAGE);
//				} else {
//					System.exit(0);
//				}
//			}
//		}
//		// ARAYLIST OF PEOPLE THAT WE WILL BE WORKING WITH!!! IMPORTANT IMPORTANT IMPORTANT!!!!!
//		ArrayList<Person> people = new ArrayList<Person>();
//		JFrame frame = new JFrame("ManyLinesAverage");
//		for (int i = 1; i <= numPeople; i++) { // Start with 1 so we don't have a number 0 which is extra
//			Person person = new Person(i);
//			people.add(person);
//		}
//		if (networkSelectString.equals("Random")) {
//			methods.befriendRandom(people, minFriends, maxFriends, new Random(), hubNumber);
//		} else if (networkSelectString.equals("Small World")) {
//			methods.befriendSmallWorld(people, minFriends, maxFriends, new Random(), hubNumber);
//		} else if (networkSelectString.equals("Scale-Free")) {
//			methods.befriendScaleFree(people, minFriends, maxFriends, new Random());
//		}
//		if (drawJung) {
//			// Creating the graph, vertices and frame
//			UndirectedSparseMultigraph<Person, String> graph = new UndirectedSparseMultigraph<Person, String>();
//			methods.drawVerticies(graph, people);
//
//			// Create vv and layout
//			int xDim = 1000;
//			int yDim = 600;
//			//LAYOUT STUFF THAT SHOULD WORK BUT PROBABLY WON"T
//			Layout<Person, String> layout = methods.chooseLayout(graph, layoutString);
//			layout.setSize(new Dimension(xDim, yDim));
//
//			VisualizationViewer<Person, String> vv = new VisualizationViewer<Person, String>(layout);
//			vv.setPreferredSize(new Dimension(xDim + 50, yDim + 50));
//			vv.getRenderContext().setVertexLabelTransformer(Person.labelByID);//Makes Labels on Vertices
//			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
//
//			// Graph Mouse
//			DefaultModalGraphMouse<Object, Object> mouse = new DefaultModalGraphMouse<Object, Object>();
//			mouse.setMode(ModalGraphMouse.Mode.PICKING);
//			vv.addKeyListener(mouse.getModeKeyListener());
//			vv.setGraphMouse(mouse);
//
//			//DrawJung
//			methods.drawJung(graph, vv, people);
//
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.add(vv);
//			frame.pack();
//			frame.setVisible(true);
//		}
//
//		// Initialize variables
//		boolean transmissionTest = false;
//		int getWellDays = 0;
//		int discovery = 0;
//		int newGetWell = 0;
//		ArrayList<Integer> infectedPeople = new ArrayList<Integer>();
//		int percentSick = 0;
//		int getVac = -1;
//		int percentTeenagers = 0;
//		int percentCurfewed = -1;
//		int curfewDays = -1;
//		ArrayList<Integer> vaccinatedPeople = new ArrayList<Integer>();
//		ArrayList<Person> teens = new ArrayList<Person>();
//		final String[] filePath = {System.getProperty("user.dir")}; //Default filepath
//		String fileName = "simResults";
//		int runTimes = 0;
//
//		done = false; // Controls when while loop stops
//
//		// Gets input from user after graph
//		while (!done) {
//			/*
//			//Get params from .config
//			int cGetWellDays = getValue(config.get(8));
//			int cPercentSick = getValue(config.get(9));
//			int cGetVac = getValue(config.get(10));
//			int cPercentTeenagers = getValue(config.get(11));
//			int cPercentCurfewed = getValue(config.get(12));
//			int cCurfewDays = getValue(config.get(13));
//			int cRunTimes = getValue(config.get(14));
//			String cFilePath = getStringValue(config.get(15));
//			String cFileName = getStringValue(config.get(16));
//			*/ // COMMENTED OUT FOR JAVA VERSION
//			
//			JPanel panel2 = new JPanel();
//			panel2.setLayout(new GridLayout(20, 0));
//
//			Checkbox transmissionTestCheckbox = new Checkbox("", null, false);
//			JTextField getWellDaysAnswer = new JTextField("10", 10);
//			JTextField discoveryAnswer = new JTextField("10000", 10);
//			JTextField newGetWellAnswer = new JTextField("5", 10);
//			JTextField initiallySickAnswer = new JTextField("10", 10);
//			JTextField vaccinatedPeopleAnswer = new JTextField("", 10);
//			JTextField percentSickAnswer = new JTextField("10", 10);
//			JTextField getVacAnswer = new JTextField("0", 10);
//			JTextField fileAnswer = new JTextField("simResults", 10);
//			JTextField runTimesAnswer = new JTextField("100", 10);
//			JTextField percentTeenagersAnswer = new JTextField("0", 10);
//			JTextField percentCurfewedAnswer = new JTextField("20", 10);
//			JTextField curfewDaysAnswer = new JTextField("50", 10);
//			final JButton browse = new JButton("Browse...");
//			filePath[0] = "C:\\Users\\Irochka\\workspace\\and\\PROEKT12";
//			browse.setText(filePath[0]);
//			//browse.setLabel(filePath[0]);
//
//			panel2.add(new JLabel("INITIAL CONDITIONS SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("What people will be initially sick? (Ex. 2,3,1,6)"));
//			panel2.add(initiallySickAnswer);
//			panel2.add(new JLabel("What people will be initially vaccinated or immune? (Ex. 8,9,20)"));
//			panel2.add(vaccinatedPeopleAnswer);
//
//			panel2.add(new JLabel("TRANSMISSION SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("Start TransmissionTest? (Does not work)"));
//			panel2.add(transmissionTestCheckbox);
//			panel2.add(new JLabel("Amount of get well days before the cure is invented?"));
//			panel2.add(getWellDaysAnswer);
//			panel2.add(new JLabel("What chance does each person have of catching the disease (___ %)?"));
//			panel2.add(percentSickAnswer);
//
//			panel2.add(new JLabel("RUNNING TO HOSPITAL SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("What percent of people run to get vaccinated when their friend gets sick?   "));
//			panel2.add(getVacAnswer);
//
//			panel2.add(new JLabel("CURE SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("How long will it take until a cure is discovered?"));
//			panel2.add(discoveryAnswer);
//			panel2.add(new JLabel("Amount of get well days after the cure is invented?"));
//			panel2.add(newGetWellAnswer);
//
//			panel2.add(new JLabel("TEENAGERS SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("What percent of teenagers should there be?"));
//			panel2.add(percentTeenagersAnswer);
//			panel2.add(new JLabel("What percent of teenagers should be curfewed?"));
//			panel2.add(percentCurfewedAnswer);
//			panel2.add(new JLabel("How long should the teenagers' curfew last?"));
//			panel2.add(curfewDaysAnswer);
//
//			panel2.add(new JLabel("RESULTS SETUP:"));
//			panel2.add(new JLabel("----------------------------------------------"));
//			panel2.add(new JLabel("How many times should the simulation run?"));
//			panel2.add(runTimesAnswer);
//			panel2.add(new JLabel("Where should I save the graph? (If not in default directory)"));
//			panel2.add(browse);
//			panel2.add(new JLabel("What should be the file-name of the output graph?"));
//			panel2.add(fileAnswer);
//
//			//Listen for browse
//			browse.addMouseListener(new MouseListener() {
//				@Override
//				public void mouseClicked(MouseEvent mouseEvent) {
//					filePath[0] = getFilePath();
//					browse.setText(filePath[0]);
//					//browse.setLabel(filePath[0]);
//				}
//
//				@Override
//				public void mousePressed(MouseEvent e) {
//
//				}
//
//				@Override
//				public void mouseReleased(MouseEvent mouseEvent) {
//
//				}
//
//				@Override
//				public void mouseEntered(MouseEvent mouseEvent) {
//
//				}
//
//				@Override
//				public void mouseExited(MouseEvent mouseEvent) {
//
//				}
//			});
//
//			int result = JOptionPane.showConfirmDialog(null, panel2, "After-Graph Configuration", JOptionPane.OK_CANCEL_OPTION);
//
//			if (result != JOptionPane.OK_OPTION) {
//				System.exit(0);
//			}
//
//			try {
//				transmissionTest = transmissionTestCheckbox.getState();
//
//				String getWellDaysString = getWellDaysAnswer.getText();
//				getWellDays = Integer.parseInt(getWellDaysString);
//				if (getWellDays < 1) {
//					throw new NumberFormatException();
//				}
//
//				String discoveryString = discoveryAnswer.getText();
//				discovery = Integer.parseInt(discoveryString);
//				if (discovery < 1) {
//					throw new NumberFormatException();
//				}
//
//				String newGetWellString = newGetWellAnswer.getText();
//				newGetWell = Integer.parseInt(newGetWellString);
//				if (newGetWell < 1) {
//					throw new NumberFormatException();
//				}
//
//				String initiallySickString = initiallySickAnswer.getText();
//				infectedPeople = MoreMethods.commaListToArrayList(initiallySickString);
//				methods.removeDuplicate(infectedPeople);
//				for (int i : infectedPeople) {
//					if (i > numPeople) {
//						throw new NumberFormatException();
//					}
//				}
//
//				String percentSickString = percentSickAnswer.getText();
//				percentSick = Integer.parseInt(percentSickString);
//				if (percentSick <= 0 || percentSick > 100) {
//					throw new NumberFormatException();
//				}
//
//				String getVacString = getVacAnswer.getText();
//				getVac = Integer.parseInt(getVacString);
//				if (getVac < 0 || getVac > 100) {
//					throw new NumberFormatException();
//				}
//
//				String vaccinatedPeopleString = vaccinatedPeopleAnswer.getText();
//				vaccinatedPeople = MoreMethods.commaListToArrayList(vaccinatedPeopleString);
//				methods.removeDuplicate(vaccinatedPeople);
//				for (int i : vaccinatedPeople) {
//					if (i > numPeople) {
//						throw new NumberFormatException();
//					}
//				}
//
//				fileName = fileAnswer.getText();
//
//				String runTimesString = runTimesAnswer.getText();
//				runTimes = Integer.parseInt(runTimesString);
//				if (runTimes <= 0) {
//					throw new NumberFormatException();
//				}
//
//				String percentTeenagersString = percentTeenagersAnswer.getText();
//				percentTeenagers = Integer.parseInt(percentTeenagersString);
//				if (percentTeenagers < 0 || percentTeenagers > 100) {
//					throw new NumberFormatException();
//				}
//				teens = methods.getAndSetTeenagers(people, percentTeenagers);
//
//				String percentCurfewedString = percentCurfewedAnswer.getText();
//				percentCurfewed = Integer.parseInt(percentCurfewedString);
//				if (percentCurfewed < 0 || percentCurfewed > 100) {
//					throw new NumberFormatException();
//				}
//
//				String curfewDaysString = curfewDaysAnswer.getText();
//				curfewDays = Integer.parseInt(curfewDaysString);
//				if (curfewDays < 0) {
//					throw new NumberFormatException();
//				}
//
//				for (int i : infectedPeople) {
//					for (int j : vaccinatedPeople) {
//						if (i == j) {
//							throw new NumberFormatException();
//						}
//					}
//				}
//
//				done = true; // Only done when go through try without errors
//			} catch (NumberFormatException e) {
//				if (result == JOptionPane.OK_OPTION) {
//					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Input is invalid.", "Input Error", JOptionPane.ERROR_MESSAGE);
//				} else {
//					System.exit(0);
//				}
//			}
//		}
//
//		Collections.sort(people, Person.orderByID);
//
//		for (int i : infectedPeople) {
//			people.get(i - 1).setSick(true);
//		}
//		for (int i : vaccinatedPeople) {
//			people.get(i - 1).setImmune(true);
//		}
//		//Record params into .config
//		params.add("numPeople`" + numPeople);
//		params.add("minFriends`" + minFriends);
//		params.add("maxFriends`" + maxFriends);
//		params.add("hubNumber`" + hubNumber);
//		params.add("networkSelectString`" + networkSelectString);
//		params.add("drawJung`" + drawJung);
//		params.add("dofWF`" + doFwF);
//		params.add("layoutString`" + layoutString);
//		params.add("getWellDays`" + getWellDays);
//		params.add("percentSick`" + percentSick);
//		params.add("getVac`" + getVac);
//		params.add("percentTeenagers`" + percentTeenagers);
//		params.add("percentCurfewed`" + percentCurfewed);
//		params.add("curfewDays`" + curfewDays);
//		params.add("runTimes`" + runTimes);
//		params.add("filePath`" + filePath[0]);
//		params.add("fileName`" + fileName);
//
//		createConfig(params);
//		//Begin actual simulations
//
//		double estimatedTime = Math.floor(0.447 * runTimes + 2768.902);
//
//		int estimatedTimeInt = (int) estimatedTime;
//
//		if (numPeople == 100) {
//			System.out.println("Estimated time: " + estimatedTimeInt + " milliseconds.");
//		}
//
//		Long startTime = System.currentTimeMillis();
//		ArrayList<ArrayList<InfoStorage>> results = new ArrayList<ArrayList<InfoStorage>>();
//
//		results = MoreMethods.simulate(people, teens, getWellDays, infectedPeople.size(), vaccinatedPeople.size(), discovery, newGetWell, percentSick, getVac, curfewDays, runTimes, percentCurfewed, transmissionTest); //Meh I don't know how to do it better
//
//		Long endTime = System.currentTimeMillis();
//
//		MoreMethods.alert("Completed " + runTimes + " simulations in " + ((endTime - startTime)) + " milliseconds.", "Complete!");
//
//
//		//Begin analysis
//
//		boolean display = false;
//		boolean displayAverages = false;
//
//		ArrayList<DayStat> days = new ArrayList<DayStat>();
//
//		//Initialize daystat array
//		for (int k = 0; k < maxDays; k++) {
//			days.add(new DayStat(k, 0, 0, 0));
//		}
//
//		//Add totals for each day
//		for (int i = 0; i < runTimes; i++) {
//			for (int j = 0; j < maxDays; j++) {
//				//System.out.println(results.get(i).size() + " " + j);
//				if (results.get(i).size() > j + 1) { //If this day is existent
//					days.get(j).setCurrentSick(days.get(j).getCurrentSick() + results.get(i).get(j).getNumSick());
//					days.get(j).setTotalSick(days.get(j).getTotalSick() + results.get(i).get(j).getTotalSick());
//					days.get(j).setCost(days.get(j).getCost() + results.get(i).get(j).getCost());
//				} else {
//					//Add 0 to numSick and cost, which is the same as doing nothing
//				}
//			}
//		}
//
//		//Get averages for each day
//		for (DayStat dayStat : days) {
//			dayStat.setCurrentSick(dayStat.getCurrentSick() / runTimes);
//			dayStat.setTotalSick(dayStat.getTotalSick() / runTimes);
//			dayStat.setCost(dayStat.getCost() / runTimes);
//		}
//
//		if (display) {
//			for (ArrayList<InfoStorage> alis : results) {
//				System.out.println("NEW RUNTIME_________________________________________________________________________");
//				for (InfoStorage is : alis) {
//					System.out.println("Welcome to day " + is.getDay() + ". Sick: " + is.getNumSick() + ". Total sick: " + is.getTotalSick() + " Cost: " + is.getCost() + ".");
//				}
//			}
//		}
//
//		if (displayAverages) {
//			for (DayStat day : days) {
//				System.out.println("Day " + day.getDay() + ". Sick: " + day.getCurrentSick() + ". Total sick: " + day.getTotalSick() + ". Cost: " + day.getCost() + ".");
//			}
//		}
//
//		//Make a graph
//
//		XYSeriesCollection dataset = new XYSeriesCollection();
//
//		XYSeries numSick = new XYSeries("Sick People");
//		XYSeries totalSick = new XYSeries("Total Sick People");
//		XYSeries cost = new XYSeries("Cost");
//
//		dataset.addSeries(numSick);
//		dataset.addSeries(totalSick);
//		dataset.addSeries(cost);
//
//		for (DayStat day : days) {
//			MoreMethods.addPoint(numSick, day.getDay(), day.getCurrentSick());
//			MoreMethods.addPoint(totalSick, day.getDay(), day.getTotalSick());
//			MoreMethods.addPoint(cost, day.getDay(), day.getCost());
//		}
//
//		MoreMethods.makeChart(dataset, filePath[0] + "/" + fileName, "Average Number of Sick People (" + runTimes + " runs) - " + networkSelectString + " Network", "Days", "Infected People (out of " + numPeople + ")");
//
//
//		//Open graph image
//		File f = new File(filePath[0] + "/" + fileName + ".png");
//		Desktop dt = Desktop.getDesktop();
//		dt.open(f);
//
//
//		if (doFwF) {
//			//Friends with Friends stuff---------------------------------------
//			methods.calculateConnectivityRatios(people);
//			UserInterface.displayMessage("The Connectivity Of this Network is: " + methods.averageConnectivityPercentage(people) + "\n"
//					+ "Standard Deviation: " + methods.standardDeviation(people) + "\n"
//					+ "Median: " + methods.medianConnectivityPrecentage(people));
//		}
//
//		if (!drawJung) { // If no graph was drawn, re-run
//			run();
//			System.exit(0);
//		}
//	}
//
//	private static String getFilePath() {
//		//Ask for graph-saving location
//		//Create a file chooser
//		final JFileChooser fc = new JFileChooser();
//
//		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//		int returnVal = fc.showOpenDialog(new JLabel("Browse..."));
//
//		String filePath = "";
//
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			File file = fc.getSelectedFile();
//			filePath = file.getAbsolutePath();
//		}
//
//		return filePath;
//	}
//
//	protected static void createConfig(ArrayList<String> contents) throws FileNotFoundException, UnsupportedEncodingException {
//		//Create configuration file
//		PrintWriter writer = new PrintWriter(".manyLinesAverageConfig", "UTF-8");
//		for (String content : contents) {
//			writer.println(content);
//		}
//		writer.close();
//	}
//	
//	/*
//	protected List<String> readFile(String path, Charset encoding) throws IOException {
//		List<String> lines = File.readAllLines(Paths.get(path), encoding);
//		return lines;
//	}
//	*/ // COMMENTED OUT FOR JAVA VERSION
//
//	protected static int getValue(String valueString) {
//		String[] parts = valueString.split("`");
//		String value = parts[1];
//		int valueInt = Integer.parseInt(value);
//		return valueInt;
//	}
//
//	protected static boolean getBoolValue(String valueString) {
//		String[] parts = valueString.split("`");
//		String value = parts[1];
//		boolean valueBool = Boolean.parseBoolean(value);
//		return valueBool;
//	}
//
//	protected static String getStringValue(String valueString) {
//		String[] parts = valueString.split("`");
//		String value = parts[1];
//		return value;
//	}
}