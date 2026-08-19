# Project Setup

This modpack uses [packwiz](https://packwiz.infra.link/) to manage mods
 - You can download [pre-built binaries](https://github.com/packwiz/packwiz/actions) or compile from source with `go install github.com/packwiz/packwiz@latest`
 - Ideally you should add the packwiz binary to your PATH environment variable and set up the [autocomplete functionality](https://packwiz.infra.link/reference/commands/packwiz/completion/)

You'll need to do more once you've installed packwiz if you want to run the modpack:
 - Packwiz uses toml files for the mods, and packwiz fetches the jars when you start the game
 - To launch with the packwiz installer, you need a MultiMC launcher (or a fork like Prism Launcher) and create a basic minecraft: 1.21.1 neoforge: 21.1.248 instance
 - Replace the minecraft directory in said instance with this repository (rename it to minecraft too)
 - Also download the [packwiz-installer-bootstrap](https://github.com/packwiz/packwiz-installer-bootstrap/releases) jar and put it your new minecraft folder
 - Go to Edit Instance -> Settings -> Custom commands, then check the Custom Commands box and paste the following command into the pre-launch command field: `"$INST_JAVA" -jar packwiz-installer-bootstrap.jar http://localhost:8080/pack.toml`

To run the modpack:
 - To update the core mod, CoreboundCore, you need to run the `gradlew modrinth`/`./tgradlew modrinth` command in the mod repository which builds the mod jar in an unlisted [modrinth mod](https://modrinth.com/mod/coreboundcore) which packwiz can fetch from
    - For more information, check the mod repository's [README](https://github.com/linmjie/CoreboundCore/blob/main/README.md)
 - Recall that your pre-launch command includes the `http://localhost:8080/` argument. This localhost server can be created via `packwiz serve` in the modpack directory, which also automatically updates the index whenever you query it
 - Now you can press play and let it run (you may also want to checkout [probejs](https://kubejs.com/wiki/addons/probejs) functionality to make scripting more streamlined)
