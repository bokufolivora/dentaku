import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage


// 電卓
fun main(args: Array<String>) {
    Application.launch(MyApplication::class.java, *args)
}

//
class MyApplication : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "電卓"

        primaryStage.setOnCloseRequest({ x -> Platform.exit() })


        primaryStage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.getResource("fxml/calculator.fxml")))

        primaryStage.show()
    }
}
