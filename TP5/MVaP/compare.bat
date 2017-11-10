@echo off


if exist compare.log del compare.log

set dirA=entry
set dirB=res

for /R %dirA% %%I in (*.*) do (
	for /R %dirB% %%i in (*.*) do (
		fc %%I %%i >> compare.log
	)
)

pause