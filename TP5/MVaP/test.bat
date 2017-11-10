@echo OFF
setlocal

if exist compare.log del compare.log
if exist effacer.log del effacer.log

set dirA=c:dir1
set dirB=c:dir2

for /R "%dirA%" %%I in (*.*) do call :_process "%%I" 1
for /R "%dirB%" %%I in (*.*) do call :_process "%%I" 2
goto :eof

:_process
if %~2==1 (
set dir1=%dirA%
set dir2=%dirB%
) else (
set dir1=%dirB%
set dir2=%dirA%
)
set relpath=%~1
call set relpath=%%relpath:%dir1%=%%
if exist "%dir2%%relpath%" (
if %~2==1 (
echo fc "%dir1%%relpath%" "%dir2%%relpath%" > NUL 2> NUL
if errorlevel 1 echo FICHIERS DIFFERENTS %dir1%%relpath% >> compare.log
) ) else (
echo %~d1%~p1%~n1%~x1 >> effacer.log
)
