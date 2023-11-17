IF EXIST ./target ( 
	ECHO "Cleaning Up..."
	CALL mvnw clean 
)

ECHO "Starting..."
CALL mvnw spring-boot:run

ECHO "Cleaning Up..."
CALL mvnw clean