if exist "%CATALINA_HOME%/jre${jdk.windows.version}/win" (
	if not "%JAVA_HOME%" == "" (
		set JAVA_HOME=
	)

	set "JRE_HOME=%CATALINA_HOME%/jre${jdk.windows.version}/win"
)

set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xms1280m -Xmx1280m -XX:MaxNewSize=256m -XX:NewSize=256m -XX:MaxMetaspaceSize=384m -XX:MetaspaceSize=384m -XX:SurvivorRatio=7"