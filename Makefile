fix:
	sbt scalafmtSbt
	sbt scalafmtAll
	sbt scalafixAll

test:
	sbt test

init:
	mkdir -p src/main/scala src/test/scala
