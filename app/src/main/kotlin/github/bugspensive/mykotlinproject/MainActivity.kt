package github.bugspensive.mykotlinproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.anko.*

open class MainActivity : Activity() {

    var myBoolean: Boolean = true

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(30)
            val name = editText()
            button("Say Hello") {
                onClick { toast("Hello, ${name.text}!") }
            }
            val mybutton = button()
            mybutton.textSize = 18f
            mybutton.id = R.id.my_button
        }

        updateUI();

    }

    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_settings,R.id.action_delete -> myBoolean = false
            R.id.action_add -> myBoolean = true
            else -> myBoolean = false
        }
        if(updateUI())
            getApplicationContext().toast(R.string.ui_updated)
        return super.onMenuItemSelected(featureId, item)
    }

    fun updateUI() : Boolean{
        val button = find<Button>(R.id.my_button)
        var buttonText: String = getResources().getString(if(myBoolean){R.string.my_button}else{R.string.not_my_button})
        button.setText(buttonText)
        return button.getText() == buttonText
    }
}
