plugins {
    distribution
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