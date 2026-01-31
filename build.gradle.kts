
// Root build file
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
