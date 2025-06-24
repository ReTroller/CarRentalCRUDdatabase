@echo off
set JDK="C:\Program Files\Java\jdk-22\jdk-22\bin\java.exe"
set FX="C:\Users\aa\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib"
set JAR="CarRentalCRUDdatabase.jar"

%JDK% --module-path %FX% --add-modules javafx.controls,javafx.fxml -jar %JAR%
pause
