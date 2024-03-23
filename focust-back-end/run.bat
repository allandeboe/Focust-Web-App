@ECHO OFF

IF EXIST ./target ( 
	ECHO.
	ECHO "Cleaning Up..."
	CALL mvnw clean 
)

ECHO.
ECHO "Starting..."
CALL mvnw spring-boot:run

ECHO.
ECHO "Cleaning Up..."
CALL mvnw clean