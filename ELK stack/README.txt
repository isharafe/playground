1. get ELK docker compose from https://github.com/deviantony/docker-elk
2. refere images in this folder for further changes.
3. logstash.conf --> output -> elasticsearch -> index -> {appName}
	{appName} is coming from the "appName" in logback-spring.xml --> pattern
