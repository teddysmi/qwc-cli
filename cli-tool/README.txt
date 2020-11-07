APPLICATION USER GUIDE

1. Prerequisites
	- jre/jdk 8; https://www.oracle.com/java/technologies/javase-jre8-downloads.html
		* If a jre/jdk is not available on your machine, download a copy suitable for your machine in the link above.
		* After installing, verify that the $PATH to you java installation has been setup with the commands below.
			echo %JAVA_HOME%
			java -version
		
		* %JAVA_HOME% should be the installation path of your jre/jdk.
		* java version should be 1.8 or above. 
		* If you have installed jre/jdk and %JAVA_HOME% is not set up, kindly follow the instructions in the link below.
			https://javatutorial.net/set-java-home-windows-10

2. Using the Application
	- Run the "executable.bat" file provided with the "*.jar" file.
	- A command prompt (by default) will pop up, running the application in the window.
	- The application will be ready for user input after it is initialized and a prompt is shown in the window.
	- The commands available to use the application are as follows.
		
		
		help
		
		This command lists the commands available.
		
		
		get
		
		This command will fetch and print the user email, password, and the recipient email configured.
		
		
		recipient johndoe@emailprovider.com
		
		This command saves the email provided to the application to the recipient field.
		
		
		sender janedoe@emailprovider.com password
		
		This command saves the log in credentials of the user for attachment download
		
		
		excel "filePath/excelFileName.xlsx"
		
		This command loads to database the data from sheets "Feb20 Acct" and "SI Level" in the excel file 
		COMMON ERROR:: Access is denied.
		TO SOLVE:: Open the excel file and turn off protected view. Access is denied in protected state.
		
	**IMPORTANT NOTE**
		All data input to the application is non persistent.
		All data is lost on application restart.
		Data need to be set up with each restart.
	
	-The scheduler fetches the .txt attachement file from the user email provided.
		It then parses the file and searches for the relevent fields in the database.
		The scheduler then sends an email to the recipient email provided with the relevant data.
	