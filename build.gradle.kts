plugins {
    distribution
    id("org.sonarqube") version "2.8"

}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.host.url", "http://10.206.20.178:9000")
        property("sonar.login", "d0faadbc6c590891ff79b546da84cf03c0c2af52")
        property("sonar.jacoco.reportPaths", "$buildDir/reports/jacoco/test.exec")
    }
}

distributions {
    main {
        contents {
            into("public") {
                from("front/build/")
            }

            from("backend/build/libs/")
        }
    }
}

tasks.findByName("distZip")!!.dependsOn(":front:build", ":backend:build")