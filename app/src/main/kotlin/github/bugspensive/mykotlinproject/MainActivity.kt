package github.bugspensive.mykotlinproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.anko.toast
import kotlinx.android.synthetic.activity_main.*

open class MainActivity : Activity() {

    var myBoolean: Boolean = true

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyTextView.setText(getResources().getString(R.string.activity))
        updateUI()
        var next: Button = findViewById(R.id.MyButton) as Button
        next.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
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
        var buttonText: String = getResources().getString(if(myBoolean){R.string.my_button}else{R.string.not_my_button})
        MyButton.setText(buttonText)
        return MyButton.getText() == buttonText
    }
}
