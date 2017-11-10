@echo off
setlocal

if exist compare.log del compare.log

set dirA=entry
set dirB=res

antlr Calculette.g4
javac *.java


for /R "%dirA%" %%I in (*.code) do (

)
pause