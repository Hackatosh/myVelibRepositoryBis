package myVelibCLUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import myVelibCore.stationPackage.Network;

public class UserLoop {
	//When you loop, you read the line entered by the user
	//The first word is used as the command
	//Then you split the line to obtain the parameters and their number
	//Regarding to the number of parameters, you deduce what the user want to do
	//You parse every parameter, and send error if parsing doesn't work
	//You call the right function
	
	public static final PrintStream ps_console = System.out;

	
	//Exit Command
	
   public static final String EXIT_COMMAND = "exit";
   public static final int NBR_PARAM_EXIT = 0;
   public static final String DESCRIPTION_EXIT =
		"exit : Exit the program\r\n"+"\n";
   
   //Help Command
   
   public static final String HELP_COMMAND = "help";
   public static final int NBR_PARAM_HELP = 0;
   public static final String DESCRIPTION_HELP = 
		"help : Call the help screen\r\n"+"\n";
   
   //Setup Command
   
   public static final String SETUP_COMMAND = "setup";
   
   public static final int NBR1_PARAM_SETUP = 1;
   public static final String DESCRIPTION1_SETUP = 
		"	setup <velibNetworkName>: to create a myVelib network with given name and\r\n" + 
   		"	consisting of 10 stations each of which has 10 parking slots and such that stations\r\n" + 
   		"	are arranged on a square grid whose of side 4km and initially populated with a 75%\r\n" + 
   		"	bikes randomly distributed over the 10 stations\r\n";
   
   public static final int NBR2_PARAM_SETUP = 5;
   public static final String DESCRIPTION2_SETUP = 
   "	setup <name> <nstations> <nslots> <sidearea> <nbikes>: to create a myVelib net-\r\n" +
   "	work with given name and consisting of nstations stations each of which has nslots\r\n" +
   "	parking slots and such that stations are arranged on a square grid whose of side\r\n" +
   "	sidearea and initially populated with a nbikes bikes randomly distributed over the\r\n" +
   "	nstations stations\r\n";
   
   public static final int NBR3_PARAM_SETUP = 6;
   public static final String DESCRIPTION3_SETUP = 
   "	setup <name> <nstations> <nslots> <sidearea> <nbikesMechanical> <nbikesElectrical>: to create a myVelib net-\r\n" +
   "	work with given name and consisting of nstations stations each of which has nslots\r\n" +
   "	parking slots and such that stations are arranged on a square grid whose of side\r\n" +
   "	sidearea and initially populated with nbikesMechanical Mechanical bikes and nBikesElectrical \r\n" +
   "	Electrical bikes randomly distributed over the nstations stations\r\n";
   
   public static final String DESCRIPTION_SETUP = 
		   "setup : used to create a myVelib network\r\n"+"\n" + DESCRIPTION1_SETUP + DESCRIPTION2_SETUP+DESCRIPTION3_SETUP+"\n";
  
   //default setup command
   public static final String DEFAULT_SETUP_COMMAND = "default_setup";
   public static final int NBR_PARAM_DEFAULT_SETUP = 2;
   public static final String DESCRITION_DEFAULT_SETUP = 
		   "default_setup <name> <sideArea>: to create a myVelib net-\r\n" +
		   "default setup : creates only a network with a name and the size of the aera's side\r\n";
   //addStation Command
   public static final String ADDSTATION_COMMAND = "addStation";
   public static final int NBR_PARAM_ADDSTATION = 6;
   public static final String DESCRIPTION_ADDSTATION = 
		   "	addStation <networkName> <stationName> <stationType> <latitude> <longitude> <nslots> : to add a station with name stationName in the network with name networkName\r\n"+
		   "	with number of slots : nslots and located in GPSCoordinates (latitude, longitude)\r\n";
   //addBicycle Command
   public static final String ADDBICYCLE_COMMAND = "addBicycle";
   public static final int NBR_PARAM_ADDBICYCLE = 3;
   public static final String DESCRIPTION_ADDBICYCLE = 
		   " 	addBicycle <networkName> <stationName> <bicycleType> : to add a bicycle of type bicycleType\r\n" +
		   "	in the station stationName of the network networkName";
   //addUser Command
   
   public static final String ADDUSER_COMMAND = "addUser";
   
   public static final int NBR1_PARAM_ADDUSER = 3;
   public static final String DESCRIPTION1_ADDUSER =
		   "	addUser <userName,cardType, velibnetworkName> : to add a user with name\r\n" +
		   "	userName and card cardType (i.e. ``none'' if the user has no card) to a myVelib net-\r\n" +
		   "	work velibnetworkName\r\n";
   
   public static final String DESCRIPTION_ADDUSER = 
		   "addUser : used to add an user\r\n"+"\n" + DESCRIPTION1_ADDUSER +"\n";
   
   // SetUserGPSPosition Command
   
 public static final String SETUSERGPSPOSITION_COMMAND = "setUserGPSPosition";
   
   public static final int NBR1_PARAM_SETUSERGPSPOSITION = 4;
   public static final String DESCRIPTION1_SETUSERGPSPOSITION =
		   "	setUserGPSPosition <velibnetworkName> <userName> <latitude> <longitude> : \r\n" +
		   "	to set the position of a given user in a given network, according to GPS coordinates\r\n"; 
   
   public static final String DESCRIPTION_SETUSERGPSPOSITION = 
		   "setUserGPSPosition : used to modify gps position of an user\r\n"+"\n" + DESCRIPTION1_SETUSERGPSPOSITION +"\n";
   
   //PlanRide Command
   
   public static final String PLANRIDE_COMMAND = "planRide";
  
   public static final int NBR1_PARAM_PLANRIDE = 6;
   public static final String DESCRIPTION1_PLANRIDE =
		   "	planRide <velibNetworkName> <userName> <policy> <destinationLatitude> <destinationLongitude> <bicycleType> \r\n" +
		   "	to plan a ride for a given user in a given network following a specific planning policy\r\n" +
		   "	You must enter the gps location and the bicycle type wished\r\n";
   
   public static final String DESCRIPTION_PLANRIDE = 
		   "planRide : used to plan a ride\r\n"+"\n" + DESCRIPTION1_PLANRIDE +"\n";
   
   
   
   //Offline Command
   
   public static final String OFFLINE_COMMAND = "offline";
   
   public static final int NBR1_PARAM_OFFLINE = 2;
   public static final String DESCRIPTION1_OFFLINE =
		   "	offline <velibnetworkName, stationName> : to put offline the station stationName\r\n"+
		   "	of the myVelib network velibnetworkName\r\n";
   
   public static final String DESCRIPTION_OFFLINE = 
		   "offline : used to put offline station"+"\n" + DESCRIPTION1_OFFLINE +"\n";
   
	//Online Command
   
   public static final String ONLINE_COMMAND = "online";

   public static final int NBR1_PARAM_ONLINE = 2;
   public static final String DESCRIPTION1_ONLINE =
		   "	online <velibnetworkName, stationName> : to put online the station stationName\r\n"+
		   "	of the myVelib network velibnetworkName\r\n";
   
   public static final String DESCRIPTION_ONLINE  = 
		   "online : used to put online station"+"\n" + DESCRIPTION1_ONLINE +"\n";
   
   //Run time Command
   
   public static final String RUNTIME_COMMAND = "runTime";
   public static final int NBR_PARAM_RUNTIME = 0;
   
   public static final String DESCRIPTION_RUNTIME = 
		   "runTime : used to launch the time simulation";
   
   //Stop time Command
   
   public static final String STOPTIME_COMMAND = "stopTime";
   public static final int NBR_PARAM_STOPTIME = 0;
   
   public static final String DESCRIPTION_STOPTIME = 
		   "stopTime : used to stop the time simulation";
   //Update Time Command
   public static final String UPDATETIME_COMMAND = "updateTime";
   public static final int NBR1_PARAM_UPDATETIME = 1;
   public static final String DESCRIPTION1_UPDATETIME =
		   "	updateTime <minutes> : update the time by a given number of minutes \r\n";
   
   public static final String DESCRIPTION_UPDATETIME= 
		   "updateTime : used to update time"+"\r\n" + DESCRIPTION1_UPDATETIME +"\r\n";
   
   //List Network Command 
   public static final String LISTNETWORK_COMMAND = "listNetwork";
   public static final int NBR_PARAM_LISTNETWORK = 0;
   
   public static final String DESCRIPTION_LISTNETWORK = 
		   "listNetwork : used to list all the networks created";
   
   //RentBike Command
   
   public static final String RENTBIKE_COMMAND = "rentBike";

   public static final int NBR1_PARAM_RENTBIKE = 3;
   public static final String DESCRIPTION1_RENTBIKE =
		   "	rentBike <userName, stationName,bycicleType> : to let the user userName renting a bike from station\r\n"+
		   "	stationName from the given type (if no bikes are available should behave accordingly)\r\n";
   
   public static final String DESCRIPTION_RENTBIKE = 
		   "rentBike : used to rent a bike"+"\n" + DESCRIPTION1_RENTBIKE +"\n";
   
   //ReturnBike Command
   
   public static final String RETURNBIKE_COMMAND = "returnBike";

   public static final int NBR1_PARAM_RETURNBIKE = 2;
   public static final String DESCRIPTION1_RETURNBIKE =
		   "	returnBike <userName, stationName> : to let the user userName returning a bike\r\n" +
		   "	to station stationName at the present instant (if no parking bay is available\r\n"+
		   "	should behave accordingly). This command should display the cost of the rent\r\n";
   
   public static final String DESCRIPTION_RETURNBIKE = 
		   "returnBike : used to return a bike"+"\n" + DESCRIPTION1_RETURNBIKE +"\n";
   
   //AddRentOperation Command
   
   public static final String ADDRENTOPERATION_COMMAND = "addRentOperation";

   public static final int NBR1_PARAM_ADDRENTOPERATION = 5;
   public static final String DESCRIPTION1_ADDRENTOPERATION =
		   "	addRentOperation <velibNetworkName> <stationName> <userName> <timeOfOperation> <numberOfParkingSlot> :\r\n" +
		   "	add a renting operation on the given station concerning the given user, parking slot and time\r\n";
   
   public static final String DESCRIPTION_ADDRENTOPERATION = 
		   "addRentOperation : add a renting operation for the statistics"+"\n" + DESCRIPTION1_ADDRENTOPERATION +"\n";
   
   //AddReturnOperation Command
   
   public static final String ADDRETURNOPERATION_COMMAND = "addReturnOperation";

   public static final int NBR1_PARAM_ADDRETURNOPERATION = 5;
   public static final String DESCRIPTION1_ADDRETURNOPERATION =
		   "	addReturnOperation <velibNetworkName> <stationName> <userName> <timeOfOperation> <numberOfParkingSlot> :\r\n" +
			"	add a returning operation on the given station concerning the given user, parking slot and time\r\n";
   
   public static final String DESCRIPTION_ADDRETURNOPERATION = 
		   "addReturnOperation : add a returning operation for the statistics"+"\n" + DESCRIPTION1_ADDRETURNOPERATION +"\n";
   
   //DisplayStation Command
   
   public static final String DISPLAYSTATION_COMMAND = "displayStation";

   public static final int NBR1_PARAM_DISPLAYSTATION = 2;
   public static final String DESCRIPTION1_DISPLAYSTATION =
		  "		displayStation<velibnetworkName, stationName> : to display the statistics\r\n"+
		  "		of station stationName of a myVelib network velibnetwork.\r\n";
   
   public static final String DESCRIPTION_DISPLAYSTATION = 
		   "displayStation : used to display a station"+"\n" + DESCRIPTION1_DISPLAYSTATION +"\n";
   
   //DisplayUser Command
   
   public static final String DISPLAYUSER_COMMAND = "displayUser";

   public static final int NBR1_PARAM_DISPLAYUSER = 2;
   public static final String DESCRIPTION1_DISPLAYUSER =
		  "		displayUser<velibnetworkName, userName> : to display the statistics\r\n"+
		  "		of user userName of a myVelib network velibnetwork.\r\n";
   
   public static final String DESCRIPTION_DISPLAYUSER = 
		   "displayUser : used to display a station"+"\n" + DESCRIPTION1_DISPLAYUSER +"\n";
   
   //SortStation Command
   
   public static final String SORTSTATION_COMMAND = "sortStation";

   public static final int NBR1_PARAM_SORTSTATION = 2;
   public static final String DESCRIPTION1_SORTSTATION =
		   "	sortStation<velibnetworkName, sortpolicy> : to display the stations in increas-\r\n"+
		   "	ing order w.r.t. to the sorting policy (as of Section ??) of user sortpolicy.\r\n";
   
   public static final String DESCRIPTION_SORTSTATION = 
		   "sortStation : used to sort the stations"+"\n" + DESCRIPTION1_SORTSTATION +"\n";
   
   //Display Command
   
   public static final String DISPLAY_COMMAND = "display";

   public static final int NBR1_PARAM_DISPLAY = 1;
   public static final String DESCRIPTION1_DISPLAY =
		   "	display <velibnetworkName>: to display the entire status (stations, parking bays,\r\n"+
		   "	users) of an a myVelib network velibnetworkName.\r\n";
   
   public static final String DESCRIPTION_DISPLAY = 
		   "display : used to display the network"+"\n" + DESCRIPTION1_DISPLAY +"\n";
   
   //ReadFile Command
   
   public static final String READFILE_COMMAND = "readFile";

   public static final int NBR1_PARAM_READFILE = 1;
   public static final String DESCRIPTION1_READFILE =
		   " readFile <fileName>: to execute the command line written in a txt file\r\n";
   
   public static final String DESCRIPTION_READFILE = 
		   "readFile : used to read a file"+"\n" + DESCRIPTION1_READFILE +"\n";
   
   //WriteToFile Command
   
   public static final String WRITETOFILE_COMMAND = "writeToFile";

   public static final int NBR1_PARAM_WRITETOFILE = 1;
   public static final String DESCRIPTION1_WRITETOFILE =
		   " writeToFile <fileName>: to write the output i a txt file. isWritingToFile must be true in this case. TO STOP, YOU MUST TYPE 'stopWriting' as the argument \r\n";
   
   public static final String DESCRIPTION_WRITETOFILE = 
		   "writeToFile : used to redirect output to a txt file "+"\n" + DESCRIPTION1_WRITETOFILE +"\n";
   //Other messages
   
   public static final String INCORRECT_PARAMETERS_NUMBER_MSG = "You've entered the wrong number of parameters for the command.\r\n"+
		   														"Type help if you need assistance.\r\n"+"\n";
   public static final String HELP_MSG = DESCRIPTION_HELP + DESCRIPTION_EXIT + DESCRIPTION_READFILE + DESCRIPTION_WRITETOFILE + DESCRIPTION_SETUP +DESCRITION_DEFAULT_SETUP+ DESCRIPTION_ADDSTATION+DESCRIPTION_ADDBICYCLE+DESCRIPTION_ADDUSER + DESCRIPTION_OFFLINE + DESCRIPTION_ONLINE + DESCRIPTION_RENTBIKE + DESCRIPTION_RETURNBIKE + DESCRIPTION_DISPLAYSTATION + DESCRIPTION_DISPLAYUSER + DESCRIPTION_SORTSTATION + DESCRIPTION_DISPLAY + DESCRIPTION_RUNTIME + DESCRIPTION_STOPTIME + DESCRIPTION_PLANRIDE + DESCRIPTION_LISTNETWORK + DESCRIPTION_ADDRENTOPERATION +DESCRIPTION_ADDRETURNOPERATION + DESCRIPTION_UPDATETIME;
   public static final String UNRECOGNIZED_COMMAND_MSG ="You entered a unrecognized command ! Remember you can type help for help ;)";
   
   public static final String READING_FILE_BEGINNING_MSG = "Beginning to read command from following file :";
   public static final String READING_FILE_COMPLETE_MSG = "Reading from file complete !";
   public static final String FAILING_TO_READ_FILE_MSG = "Failing to read the file because of an incorrect path... Please type an other command";
   
   public static final String EXITING_MSG = "Exit program...";
   public static final String DEADLOCK_EXITING_MSG = "You've ended in a deadlock ! :( Please contact the author of the program in order to fix this bug !!!\r\n" +
		   											 "Now Exiting...";
   // ==== MAIN ====
   
   public static void main(final String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Welcome to MyVelib 1.0 !");
      System.out.println("Enter a command or type " + HELP_COMMAND + " for help. Type " + EXIT_COMMAND + " to quit.");
      
      boolean isUserInsideDeadlock;
      boolean listenToUser = true;
      boolean listenToAFile = false;
      String command = "";
      String[] inputForParsing = null;
      int numberOfParametersEntered = 0;
      Scanner txtFromFile = null;
      
      while (true) {
    	 
    	  isUserInsideDeadlock = true; // To verify that the user can type a command or a file is read, else the program will exit
    	 
    	  if(listenToAFile) {
      		isUserInsideDeadlock = false;
      		if(txtFromFile.hasNextLine()) {
      			String input = txtFromFile.nextLine();
      			inputForParsing = input.split(" ");
         		 	command = inputForParsing[0];
         		 	numberOfParametersEntered = inputForParsing.length-1;
      			System.out.println(input);
      		}
      		else {
      			System.out.println(READING_FILE_COMPLETE_MSG);
      			listenToUser = true;
      			listenToAFile = false;
      			txtFromFile.close();
      		}
      	 } 
    	  
    	 if(listenToUser) {
    		 isUserInsideDeadlock = false;
    		 System.out.print("> ");
    		 String input = br.readLine();
    		 inputForParsing = input.split(" ");
    		 command = inputForParsing[0];
    		 numberOfParametersEntered = inputForParsing.length-1;
    	 }
    	 
    	 
    	 
    	 if(isUserInsideDeadlock) {
    		 System.out.println(DEADLOCK_EXITING_MSG);
    		 return;
    	 }
    
    	 // CHECKING OF SPECIAL COMMAND
         
         if (command.equalsIgnoreCase(EXIT_COMMAND)) {
        	 if (numberOfParametersEntered == NBR_PARAM_EXIT) {
                 System.out.println(EXITING_MSG);
                 return;
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(HELP_COMMAND)) {
        	 if(numberOfParametersEntered == NBR_PARAM_HELP) {
        	 System.out.print(HELP_MSG);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(READFILE_COMMAND)) {
        	 if(numberOfParametersEntered == NBR1_PARAM_READFILE) {
        		 try{
        		 String fileName = inputForParsing[1];
        		 File file = new File(fileName);
        		 txtFromFile = new Scanner(file);
        		 System.out.println(READING_FILE_BEGINNING_MSG +" "+ fileName);
        		 listenToUser = false;
        		 listenToAFile = true;
        		 }
        		 catch(Exception e) {
        			System.out.println(FAILING_TO_READ_FILE_MSG);
         			listenToUser = true;
        			listenToAFile = false;
        		 }
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(WRITETOFILE_COMMAND)) {
        	 if(numberOfParametersEntered == NBR1_PARAM_WRITETOFILE) {
        		 String fileName = inputForParsing[1];
        		 if(fileName.equalsIgnoreCase("stopWriting")){
        			 System.setOut(ps_console);
        		 }
        		 else {
        			 File file = new File(fileName);
        	         FileOutputStream fos = new FileOutputStream(file);
        	         PrintStream ps = new PrintStream(fos);
        	         System.setOut(ps);
        		 }
        	 }
         }
         
         // OTHER COMMANDS
         
         else if (command.equalsIgnoreCase(SETUP_COMMAND)) {
        	if(numberOfParametersEntered==NBR1_PARAM_SETUP) {
        		ParsingAndCalling.setupWith1Param(inputForParsing);
        	}
        	else if(numberOfParametersEntered==NBR2_PARAM_SETUP) {
        		ParsingAndCalling.setupWith5Param(inputForParsing);
        	}
        	else if (numberOfParametersEntered==NBR3_PARAM_SETUP) {
        		ParsingAndCalling.setupWith6Param(inputForParsing);
        	}
        	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(DEFAULT_SETUP_COMMAND)) {
        	 if(numberOfParametersEntered == NBR_PARAM_DEFAULT_SETUP) {
        		 ParsingAndCalling.defaultSetUpWith2Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);} 
         }
         else if (command.equalsIgnoreCase(ADDSTATION_COMMAND)) {
        	 if(numberOfParametersEntered == NBR_PARAM_ADDSTATION) {
        		 ParsingAndCalling.addStationWith6Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(ADDBICYCLE_COMMAND)) {
        	 if(numberOfParametersEntered == NBR_PARAM_ADDBICYCLE) {
        		 ParsingAndCalling.addBicycleWith3Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(ADDUSER_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_ADDUSER) {
         		ParsingAndCalling.addUserWith3Param(inputForParsing);;
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(OFFLINE_COMMAND)) {
          	if(numberOfParametersEntered==NBR1_PARAM_OFFLINE) {
          		ParsingAndCalling.offlineWith2Param(inputForParsing);
          	}
          	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(ONLINE_COMMAND)) {
           	if(numberOfParametersEntered==NBR1_PARAM_ONLINE) {
           		ParsingAndCalling.onlineWith2Param(inputForParsing);
           	}
           	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(RENTBIKE_COMMAND)) {
            	if(numberOfParametersEntered==NBR1_PARAM_RENTBIKE) {
            		ParsingAndCalling.rentBikeWith2Param(inputForParsing);
            	}
            	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
          }
         
         else if (command.equalsIgnoreCase(RETURNBIKE_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_RETURNBIKE) {
         		ParsingAndCalling.returnBikeWith3Param(inputForParsing);
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAYSTATION_COMMAND)) {
          	if(numberOfParametersEntered==NBR1_PARAM_DISPLAYSTATION) {
          		ParsingAndCalling.displayStationWith2Param(inputForParsing);
          	}
          	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAYUSER_COMMAND)) {
           	if(numberOfParametersEntered==NBR1_PARAM_DISPLAYUSER) {
           		ParsingAndCalling.displayUserWith2Param(inputForParsing);
           	}
           	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(SORTSTATION_COMMAND)) {
            	if(numberOfParametersEntered==NBR1_PARAM_SORTSTATION) {
            		ParsingAndCalling.sortStationWith2Param(inputForParsing);
            	}
            	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAY_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_DISPLAY) {
         		ParsingAndCalling.displayWith1Param(inputForParsing);
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(RUNTIME_COMMAND)) {
        	 if(numberOfParametersEntered==NBR_PARAM_RUNTIME) {
        	 ParsingAndCalling.runTimeWith0Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(STOPTIME_COMMAND)) {
        	 if(numberOfParametersEntered==NBR_PARAM_STOPTIME) {
        	 ParsingAndCalling.stopTimeWith0Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(LISTNETWORK_COMMAND)) {
        	 if(numberOfParametersEntered==NBR_PARAM_LISTNETWORK) {
        	 ParsingAndCalling.listNetworkWith0Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(PLANRIDE_COMMAND)) {
        	 if(numberOfParametersEntered==NBR1_PARAM_PLANRIDE) {
        	 ParsingAndCalling.planningRideWith6Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(SETUSERGPSPOSITION_COMMAND)) {
        	 if(numberOfParametersEntered==NBR1_PARAM_SETUSERGPSPOSITION) {
        	 ParsingAndCalling.setUserGPSPositionWith4Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(ADDRENTOPERATION_COMMAND)) {
        	 if(numberOfParametersEntered==NBR1_PARAM_ADDRENTOPERATION) {
        	 ParsingAndCalling.addRentOperationWith5Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(ADDRETURNOPERATION_COMMAND)) {
        	 if(numberOfParametersEntered==NBR1_PARAM_ADDRETURNOPERATION) {
        	 ParsingAndCalling.addReturnOperationWith5Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else if (command.equalsIgnoreCase(UPDATETIME_COMMAND)) {
        	 if(numberOfParametersEntered==NBR1_PARAM_UPDATETIME) {
        	 ParsingAndCalling.updateTimeWith1Param(inputForParsing);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         else{System.out.println(UNRECOGNIZED_COMMAND_MSG);}
      }
   }
}