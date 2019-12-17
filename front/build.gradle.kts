group = "com.hw.tools.route"
version = "0.0.1-SNAPSHOT"

task<Exec>("build") {
    executable("yarn")
    args("build")
}

task<Exec>("test") {
    executable("yarn")
    args("test")
}

task<Exec>("clean") {
    executable("rm")
    args("-rf", "./build/")
}
