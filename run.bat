@ECHO OFF

ECHO. 
ECHO "Checking Docker"
CALL docker --version

ECHO.
ECHO "Building..."
CALL docker-compose build

ECHO.
ECHO "Booting up..."
START /wait docker compose up 

ECHO.
ECHO "Shutting down..."
CALL docker compose down --remove-orphans

ECHO.
ECHO "Done!"