package polak.shay.test.attenti.View.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_display.*
import polak.shay.test.attenti.MyApp
import polak.shay.test.attenti.R
import android.view.View
import android.widget.Toast
import polak.shay.test.attenti.Model.Finder
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.View.Adapters.ColDisplayAdapter
import polak.shay.test.attenti.View.Adapters.MatrixDisplayAdapter


class DisplayActivity : AppCompatActivity(), Finder.FinderListener {

    private var mAdapter: MatrixDisplayAdapter? = null
    private var mColNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        setupView()
    }


    private fun setupView() {
        val matrix = MyApp.instance.getData()
        displayMatrix.setLayoutManager( GridLayoutManager(this, matrix!!.getCol()))
        mAdapter = MatrixDisplayAdapter(this, matrix)
        displayMatrix.adapter = mAdapter
    }

    fun paintMatix(v: View) {
        mAdapter?.RemoveClickListener()
        //v.setOnClickListener(null) reset
        Finder(MyApp.instance.getData()!!, this)
    }

    override fun returnIslandNumber(matrix: Matrix, islandNumber: Int) {
        mAdapter?.updateDate(matrix)
        Toast.makeText(this, "the number is $islandNumber", Toast.LENGTH_LONG).show()
    }
}