# Currency Conversion Application

- Spring Boot application source code in `converter/` folder.
- Frontend React.js applcation in `frontend/` folder.
- Spring boot REST API for currency conversion deployed on Heroku at https://currencyconversionapi.herokuapp.com

## API Endpoints
- https://currencyconversionapi.herokuapp.com/convert
    - POST
    - Request Body:
        - amount (double)
        - source (String)
        - target (String)
    - Returns:
        - success (boolean)
        - value (double)
        - currency (String)
        - message (String)
    - Description:
        - Takes in amount, source symbol and target symbol as input and returns converted value.
- https://currencyconversionapi.herokuapp.com/getSymbols
    - GET
    - Description
        - Queries https://exchangeratesapi.io/ for their supported currency symbols and returns as an array of Strings.