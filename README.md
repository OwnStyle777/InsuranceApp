#Insurance application

Web application provides intuitive UI and several feutures for registered users.

In app is implemented registration, after successfully validation of data in frontend, 
data are sendeted to the backend for validation, if backend validate data send http message to frontend ,save data to database
and frontend displays message on client side

When is registration process completed, you can log-in , frontend validate data, send data to backend. After validation 
backend generate the autentification token, and add to the cookies. Frontedn accept this token send it back to the backend,
and backen validate token and send data about client to frontend.

After this process on the client side you can, display different informations about clients , personal data , insurance info.
In application you can navigate by te upper navigation bar or side bar or you can use dropdown menu.

You can use liabitity insurance calculator, you put some parameters and calculator calculate the averege price of insurance.
You can also see the circle, and color changed after level of price.

In this application are implemented color modes ,dark and light.

For better security are passwords hashed and after this saved to the database.
