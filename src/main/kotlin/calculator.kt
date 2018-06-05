import javafx.fxml.FXML
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

// 電卓：アンドロイド用に作ったコードを流用して
// fxml は作り直しました
class calculator : Initializable {
    var     answerVal = 0               // 表示している値
    var     inputlen  = 0               // 桁数
    var     memoVal   = 0               // 一時記憶

    // 演算モード
    enum class calMode {
        NOTMAL,
        PLUS,
        MINUS,
        MULT,
        DIV,
        EQUAL
    }
    var     calModeNew : calMode = calMode.NOTMAL

    @FXML lateinit var answerView : Label
    @FXML lateinit var button_0 : Button
    @FXML lateinit var button_1 : Button
    @FXML lateinit var button_2 : Button
    @FXML lateinit var button_3 : Button
    @FXML lateinit var button_4 : Button
    @FXML lateinit var button_5 : Button
    @FXML lateinit var button_6 : Button
    @FXML lateinit var button_7 : Button
    @FXML lateinit var button_8 : Button
    @FXML lateinit var button_9 : Button
    @FXML lateinit var button_add : Button
    @FXML lateinit var button_divide : Button
    @FXML lateinit var button_multiply : Button
    @FXML lateinit var button_equal : Button
    @FXML lateinit var button_minus : Button
    @FXML lateinit var buttonClear : Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // ボタンの登録
        this.buttonClear.onAction = EventHandler {
            answerVal = 0
            inputlen = 0
            memoVal = 0
            calModeNew = calMode.NOTMAL
            answerView.text = answerVal.toString()
        }
        button_0.onAction = EventHandler { valAdd(0) }
        button_1.onAction = EventHandler { valAdd(1) }
        button_2.onAction = EventHandler { valAdd(2) }
        button_3.onAction = EventHandler { valAdd(3) }
        button_4.onAction = EventHandler { valAdd(4) }
        button_5.onAction = EventHandler { valAdd(5) }
        button_6.onAction = EventHandler { valAdd(6) }
        button_7.onAction = EventHandler { valAdd(7) }
        button_8.onAction = EventHandler { valAdd(8) }
        button_9.onAction = EventHandler { valAdd(9) }
        button_add.onAction = EventHandler {      calModeCheng(calMode.PLUS) }
        button_divide.onAction = EventHandler {   calModeCheng(calMode.DIV) }
        button_multiply.onAction = EventHandler { calModeCheng(calMode.MULT) }
        button_minus.onAction = EventHandler {    calModeCheng(calMode.MINUS) }
        button_equal.onAction = EventHandler {    calModeCheng(calMode.EQUAL) }


    }
    // 数値ボタン押された 表示値×１０して１桁目に加算
    private fun valAdd( item : Int ) {
        if ( inputlen == 0) { answerVal = 0 }
        if ( 10 > inputlen ) {
            inputlen ++
            answerVal = answerVal * 10 +item
        }
        // Scene BuilderでtextAlignmentを右詰めにしても左になってしまう
        answerView.setText( answerVal.toString())
    }
    // 演算ボタンおされた
    private fun calModeCheng( setMode : calMode ) {
        // 入力があったら
        if ( 0 < inputlen ) {
            // 前の演算モードに応じて計算
            when( calModeNew ) {
                calMode.PLUS -> answerVal += memoVal
                calMode.MINUS -> answerVal = memoVal - answerVal
                calMode.MULT -> answerVal = memoVal * answerVal
                calMode.DIV -> answerVal = if ( 0 == answerVal) 0 else memoVal/answerVal
                else -> {}
            }
        }
        // 次の計算の準備
        inputlen = 0
        calModeNew = setMode
        memoVal = answerVal
        // 結果表示
        answerView.setText( answerVal.toString())
    }
}

// javaFX 電卓で探すともっと良いコード出てきます