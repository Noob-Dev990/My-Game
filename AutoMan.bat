@echo off
title AutoMan
del /q *.class 2>nul 
javac *.java
java Core
pause