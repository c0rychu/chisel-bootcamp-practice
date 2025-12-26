fix:
	sbt scalafmtSbt
	sbt scalafmtAll
	sbt scalafixAll
init:
	mkdir -p src/main/scala src/test/scala
