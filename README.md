glucose
=======

Server management (similar to Fabric) in Groovy

To use

## Clone Repository

```bash
git clone git://github.com/parj/glucose.git
```

## Build projects
*Ensure gradle is already there*

```bash
gradle build
```

## To execute

> Setup connection params to your environment in org.pm.environments.local
> `gradle build`

Example
> uptime (only available on Unix) - `gradle run -Denv=local -Dtask=Utilities.uptime`
> ls - `gradle run -Denv=local -Dtask=Utilities.runCommand -Dargs="ls"`