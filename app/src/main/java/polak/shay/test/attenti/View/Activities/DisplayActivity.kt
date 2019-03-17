package polak.shay.test.attenti.View.Activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_display.*
import polak.shay.test.attenti.MyApp
import polak.shay.test.attenti.R
import polak.shay.test.attenti.View.Adapters.MatrixDisplayAdapter
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import polak.shay.test.attenti.Model.Finder
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.View.Adapters.ColDisplayAdapter


class DisplayActivity : AppCompatActivity(), Finder.FinderListener {

    private var mAdapter: ColDisplayAdapter? = null
    private var mColNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        setupView()
    }

    private fun setupView() {
        val matrix = MyApp.instance.getData()
        displayMatrix.setLayoutManager(LinearLayoutManager(this))
        mAdapter = ColDisplayAdapter(this, matrix!!.getCol(mColNumber))
        displayMatrix.adapter = mAdapter
    }

    fun paintMatix(v: View) {
        mAdapter?.RemoveClickListener()
        Finder(MyApp.instance.getData()!!, this)
    }

    fun previous(v: View) {
        if (mColNumber - 1 > 0) {
            mColNumber--
            mAdapter!!.updateDate(MyApp.instance.getData()?.getCol(mColNumber))
        }
    }

    fun next(v: View)
    {
        if (mColNumber + 1 < MyApp.instance.getData()!!.getCol()) {
            mColNumber++
            mAdapter!!.updateDate(MyApp.instance.getData()?.getCol(mColNumber))
        }
    }

    override fun returnIslandNumber(matrix: Matrix, islandNumber: Int) {
        MyApp.instance.setData(matrix)
        mAdapter?.updateDate(matrix.getCol(mColNumber))
        Toast.makeText(this, "the number is $islandNumber", Toast.LENGTH_LONG).show()
    }
}