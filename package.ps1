if (test-path dist){
    remove-item dist -recurse -force
}

jpackage `
  --type app-image `
  --name NumericalMethods `
  --input target `
  --main-jar NumericalMethods-0.0.1.jar `
  --runtime-image custom-runtime `
  --dest dist `
  --app-version 0.0.1 `
  --icon src/main/resources/appIcon.ico

# change the app-version to the version of the application.