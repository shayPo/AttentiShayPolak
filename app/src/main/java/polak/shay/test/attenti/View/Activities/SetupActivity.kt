package polak.shay.test.attenti.View.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setup.*
import polak.shay.test.attenti.Model.Creator
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.MyApp
import polak.shay.test.attenti.R
import polak.shay.test.attenti.R.string.col

class SetupActivity : AppCompatActivity(), Creator.CreatorListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
    }

    fun onCreate(v : View)
    {
        val col = cols.text.toString().toInt()
        val row = rows.text.toString().toInt()

        if(row < 1 || col < 1)
        {
            Toast.makeText(this, R.string.input_size_error, Toast.LENGTH_LONG).show()
        }
        else
        {
            v.setOnClickListener(null)
            Creator(col, row, this)
        }
    }

    override fun result(m: Matrix) {
        val display = Intent(this, DisplayActivity::class.java)
        MyApp.instance.setData(m)
        startActivity(display)
    }
}