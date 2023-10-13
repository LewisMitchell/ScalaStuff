# Scala Play run modes

There are 3 different run modes available in Scala Play

- Production
- Development
- Test

To start the service in Production mode, run `sbt start`

To start the service in Development mode, run `sbt run` (most common)

To 'start' the service in Test mode, run `sbt test`

## Key differences between Production and Development mode

In Production mode, if a runtime error were to occur in the code then the exception is logged with an ID. 
In Development mode, when this happens, a sample of the code where the failure occurred is shown (useful for debugging).

When the service is started in Development mode, it is lazily loaded such that classes are not injected or compiled until the first request
to the service is made. In Production mode, the service is eagerly started which means all classes are injected and compiled before the service starts accepting requests.

An `Environment` class is available in Play which detects what mode the service was started in, this could be useful for loading different configuration files or keys such as:
```
if (environment.mode == Mode.Dev) config.get[String]("dev.config") 
else if(environment.mode == Mode.Prod) config.get[String]("prod.config")
```

A secret key is needed in a Scala Play project to encrypt the session data (via a config key called `play.http.secret.key`).
If a secret key is not set, the application will not start in Production mode (it will fail with an exception), however the service will start
and accept requests in Development mode despite not having a secret key set.