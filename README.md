## Overview
This repository contains the loom plugin for JobRunr.

By adding this plugin on JDK 16 (still in Early Access at the time of writing) that has Project Loom available, JobRunr will automatically make use of Virtual Threads.  


## Usage
As Project Loom is still in Preview, this library is not yet available on Maven Central.

- Install JDK16 with Project Loom
- Adapt `gradle.properties` and make sure the path to JDK16 is correct
- Build the project and publish the jar to your Local Maven repository
- Next to the standard JobRunr jar, also add the jar `org.jobrunr.plugins:jobrunr-loom:1.0-SNAPSHOT`
- Run your project and make sure you see "JobRunrExecutor of type 'VirtualThreadJobRunrExecutor' started" in the logs

A complete example is available here: https://github.com/jobrunr/example-salary-slip/tree/loom

More info about JobRunr and Loom is available in the blog post [Project Loom, Virtual Threads and JobRunr](https://www.jobrunr.io/blog/project-loom-virtual-threads-and-jobrunr/)
  