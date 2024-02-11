# Insurance application

The Insurance Application is a web-based platform that offers an intuitive user
interface along with a range of features for registered users.

# Registration Process
Before accessing the application, users are guided through a seamless registration process. 
Once the user inputs their information via the frontend, the data undergoes validation, first on the frontend and then on the backend. 
After successful validation, the data is securely transmitted to the backend via HTTP requests. 
The backend then performs additional validation before saving the data to the database. 
A confirmation message is displayed on the client side, notifying the user of a successful registration

 # Authentication and Login
Following registration, users can log in to the application. The frontend once again validates the login data before sending it to the backend. 
After successful validation, the backend generates an authentication token, which is securely stored in cookies. 
This token is  transmitted back to the backend, facilitating secure and authenticated communication between the client and the server.

# User Interface
Once logged in, users gain access to a  UI where they can view and manage various aspects of their account. 
This includes personal information, insurance details, and other relevant data. 
Navigation within the application is facilitated through intuitive menus located at the top 
and side of the interface, as well as dropdown menus for quick access to specific sections.

# Liability insurance calculator
One of the key features of the Insurance Application is the liability insurance calculator. 
Users can input relevant parameters, and the calculator computes the average price of insurance based on the provided data. 
Additionally, a visual representation in the form of a color-coded circle provides users with a quick indicator of the insurance price level.

# Customizable Color Modes
To enhance user experience and accessibility, the application offers support for both light and dark color modes. 
Users have the flexibility to customize their preferred color scheme based on their preferences and viewing conditions.

# Security Measures
Ensuring the security of user data is a top priority. 
Passwords are securely hashed before being stored in the database, to prevent the risk of unauthorized access and ensuring data integrity and confidentiality.


# Author Martin Hároník
