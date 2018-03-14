:begin
@echo off
mvn sonar:sonar \
  -Dsonar.organization=healpot-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=37179c4bee358d75ef6edf492b0689a287340019
goto begin